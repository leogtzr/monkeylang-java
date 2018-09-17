package com.monkey.lang.parser;

import com.monkey.lang.ast.Identifier;
import com.monkey.lang.ast.LetStatement;
import com.monkey.lang.ast.Program;
import com.monkey.lang.ast.Statement;
import com.monkey.lang.lexer.Lexer;
import com.monkey.lang.token.Token;
import com.monkey.lang.token.TokenLiterals;
import com.monkey.lang.token.TokenType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Parser {

    private Lexer lexer;
    private Token curToken;
    private Token peekToken;
    private List<String> errors;

    public Parser() {
        this.errors = new ArrayList<>();
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

    public static Parser New(final Lexer lexer) {
        final Parser parser = new Parser();
        parser.setLexer(lexer);

        // Read two tokens, so curToken and peekToken are both set
        parser.nextToken();
        parser.nextToken();

        return parser;
    }

    public Program parseProgram() {

        final Program program = new Program();
        //program.setStatements(new ArrayList<>());

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
                return null;
        }
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

        if (!this.curTokenIs(new TokenType(TokenLiterals.SEMICOLON))) {
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

}