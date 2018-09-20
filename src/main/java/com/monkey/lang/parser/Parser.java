package com.monkey.lang.parser;

import com.monkey.lang.ast.InfixParseFunction;
import com.monkey.lang.ast.*;
import com.monkey.lang.lexer.Lexer;
import com.monkey.lang.token.Token;
import com.monkey.lang.token.TokenLiterals;
import com.monkey.lang.token.TokenType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.monkey.lang.ast.Precedence.LOWEST;
import static com.monkey.lang.ast.Precedence.PREFIX;

public final class Parser {

    private Lexer lexer;
    private Token curToken;
    private Token peekToken;
    private List<String> errors;

    private Map<TokenType, PrefixParseFunction> prefixParseFns = null;
    private Map<TokenType, InfixParseFunction> infixParseFns = null;

    public Parser(final Lexer lexer) {
        this.errors = new ArrayList<>();
        // final Parser parser = new Parser();
        this.lexer = lexer;

        /*
            p.prefixParseFns = make(map[token.TokenType]prefixParseFn)
            p.registerPrefix(token.IDENT, p.parseIdentifier)
            p.registerPrefix(token.INT, p.parseIntegerLiteral)

            p.registerPrefix(token.BANG, p.parsePrefixExpression)
            p.registerPrefix(token.MINUS, p.parsePrefixExpression)
         */
        final Map<TokenType, PrefixParseFunction> prefixFunctions = new HashMap<>();
        prefixFunctions.put(new TokenType(TokenLiterals.IDENT), parseIdentifier());
        prefixFunctions.put(new TokenType(TokenLiterals.INT), parseIntegerLiteral());

        prefixFunctions.put(new TokenType(TokenLiterals.BANG), parsePrefixExpression());
        prefixFunctions.put(new TokenType(TokenLiterals.MINUS), parsePrefixExpression());

        this.setPrefixParseFns(prefixFunctions);

        final Map<TokenType, InfixParseFunction> infixFunctions = new HashMap<>();
        this.setInfixParseFns(infixFunctions);

        // Read two tokens, so curToken and peekToken are both set
        this.nextToken();
        this.nextToken();

        // return parser;

    }

    private PrefixParseFunction parsePrefixExpression() {
        return () -> {
            final PrefixExpression expression = new PrefixExpression();
            expression.setToken(this.curToken);
            expression.setOperator(curToken.getLiteral());

            this.nextToken();

            expression.setRight(parseExpression(PREFIX));

            return expression;
        };
    }

    private PrefixParseFunction parseIdentifier() {
        return () -> new Identifier(this.curToken, this.curToken.getLiteral());
    }

    private PrefixParseFunction parseIntegerLiteral() {
        return () -> {
            final IntegerLiteral lit = new IntegerLiteral();
            lit.setToken(this.curToken);
            try {
                final int value = Integer.parseInt(this.curToken.getLiteral());
                lit.setValue(value);
            } catch (final NumberFormatException ex) {
                final String msg = String.format("could not parse %s as Integer", this.curToken.getLiteral());
                this.errors.add(msg);
                return null;
            }
            return lit;
        };
    }

    public Lexer getLexer() {
        return lexer;
    }

    public void setLexer(final Lexer lexer) {
        this.lexer = lexer;
    }

    public Token getCurToken() {
        return curToken;
    }

    public void setCurToken(final Token curToken) {
        this.curToken = curToken;
    }

    public Token getPeekToken() {
        return peekToken;
    }

    public void setPeekToken(final Token peekToken) {
        this.peekToken = peekToken;
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public void setErrors(final List<String> errors) {
        this.errors = errors;
    }

    public void nextToken() {
        this.curToken = this.peekToken;
        this.peekToken = this.lexer.nextToken();
    }

    public Program parseProgram() {

        final Program program = new Program();

        while (!this.getCurToken().getType().equals(new TokenType(TokenLiterals.EOF))) {
            final Statement stmt = this.parseStatement();
            if (stmt != null) {
                program.addStatement(stmt);
            }
            this.nextToken();
        }
        return program;
    }

    public Statement parseStatement() {

        switch (this.curToken.getType().toString()) {
            case TokenLiterals.LET:
                return this.parseLetStatement();
            case TokenLiterals.RETURN:
                return this.parseReturnStatement();
            default:
                return parseExpressionStatement();
        }
    }

    private ExpressionStatement parseExpressionStatement() {
        final ExpressionStatement stmt = new ExpressionStatement();
        stmt.setToken(this.curToken);

        final Expression expression = parseExpression(LOWEST);
        stmt.setExpression(expression);

        if (this.peekTokenIs(new TokenType(TokenLiterals.SEMICOLON))) {
            this.nextToken();
        }

        return stmt;
    }

    private Expression parseExpression(final int precedence) {
        final PrefixParseFunction prefix = this.prefixParseFns.get(this.curToken.getType());
        if (prefix == null) {
            this.noPrefixParseFnError(this.curToken.getType());
            return null;
        }
        final Expression leftExp = prefix.function();
        return leftExp;
    }

    private void noPrefixParseFnError(final TokenType tokenType) {
        final String msg = String.format("no prefix parse function for %s found", tokenType);
        this.errors.add(msg);
    }


    private LetStatement parseLetStatement() {
        final LetStatement stmt = new LetStatement();
        stmt.setToken(this.getCurToken());

        if (!this.expectPeek(new TokenType(TokenLiterals.IDENT))) {
            return null;
        }

        final Identifier identifier = new Identifier();
        identifier.setToken(this.getCurToken());
        identifier.setValue(this.getCurToken().getLiteral());

        stmt.setName(identifier);

        if (!this.expectPeek(new TokenType(TokenLiterals.ASSIGN))) {
            return null;
        }

        while (!this.curTokenIs(new TokenType(TokenLiterals.SEMICOLON))) {
            this.nextToken();
        }

        return stmt;
    }

    private ReturnStatement parseReturnStatement() {

        final ReturnStatement stmt = new ReturnStatement();
        stmt.setToken(this.getCurToken());

        this.nextToken();

        // We're skipping the expression until we
        // encounter a semicolon.
        while (!this.curTokenIs(new TokenType(TokenLiterals.SEMICOLON))) {
            this.nextToken();
        }

        return stmt;
    }

    private boolean expectPeek(final TokenType t) {
        if (this.peekTokenIs(t)) {
            this.nextToken();
            return true;
        }
        this.peekError(t);
        return false;
    }

    private void peekError(final TokenType t) {
        final String msg = String.format("expected next token to be %s, got %s instead", t,this.getPeekToken().getType());
        this.errors.add(msg);
    }

    private boolean curTokenIs(final TokenType t) {
        return this.getCurToken().getType().equals(t);
    }

    private boolean peekTokenIs(final TokenType t) {
        return this.getPeekToken().getType().equals(t);
    }

    public Map<TokenType, PrefixParseFunction> getPrefixParseFns() {
        return prefixParseFns;
    }

    public Map<TokenType, InfixParseFunction> getInfixParseFns() {
        return infixParseFns;
    }

    public void setPrefixParseFns(final Map<TokenType, PrefixParseFunction> prefixParseFns) {
        this.prefixParseFns = prefixParseFns;
    }

    public void setInfixParseFns(final Map<TokenType, InfixParseFunction> infixParseFns) {
        this.infixParseFns = infixParseFns;
    }
}