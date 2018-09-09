package com.monkey.lang.lexer;

import com.monkey.lang.token.Token;
import com.monkey.lang.token.TokenLiterals;
import com.monkey.lang.token.TokenType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {

    @Test
    void nextToken() {
        final String INPUT = "=+(){},;";

        final Token[] tests = {
                new Token(new TokenType(TokenLiterals.ASSIGN), "="),
                new Token(new TokenType(TokenLiterals.PLUS), "+"),
                new Token(new TokenType(TokenLiterals.LPAREN), "("),
                new Token(new TokenType(TokenLiterals.RPAREN), ")"),
                new Token(new TokenType(TokenLiterals.LBRACE), "{"),
                new Token(new TokenType(TokenLiterals.RBRACE), "}"),
                new Token(new TokenType(TokenLiterals.COMMA), ","),
                new Token(new TokenType(TokenLiterals.SEMICOLON), ";"),
        };

        final Lexer lexer = Lexer.New(INPUT);

        for (final Token test : tests) {
            Token tok = lexer.nextToken();
            assertEquals(tok.getType(), test.getType());
            assertEquals(tok.getLiteral(), test.getLiteral());
        }

    }
}