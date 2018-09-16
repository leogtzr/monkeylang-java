package com.monkey.lang.parser;

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
        return Collections.unmodifiableList(this.errors);
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
        program.setStatements(new ArrayList<>());

        while (this.curToken.getType().equals(new TokenType(TokenLiterals.EOF))) {
            final Statement stmt = this.parseStatement();
            if (stmt != null) {
                program.getStatements().add(stmt);
            }
            this.nextToken();
        }

        return program;
    }

    public Statement parseStatement() {

        switch (this.curToken.getType()) {
            case TokenLiterals.LET:
                return this.parseLetStatement();
            case TokenLiterals.RETURN:
                return this.parseReturnStatement();
            default:
                return null;
        }

//        switch p.curToken.Type {
//            case token.LET:
//                return p.parseLetStatement()
//            case token.RETURN:
//                return p.parseReturnStatement()
//            default:
//                return nil
//        }
    }

    private Statement parseLetStatement() {
        stmt := &ast.LetStatement{Token: p.curToken};

        if !p.expectPeek(token.IDENT) {
            return nil
        }

        stmt.Name = &ast.Identifier{Token: p.curToken, Value: p.curToken.Literal}

        if !p.curTokenIs(token.SEMICOLON) {
            p.nextToken()
        }

        return stmt
    }

}