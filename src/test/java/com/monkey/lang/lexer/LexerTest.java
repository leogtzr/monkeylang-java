package com.monkey.lang.lexer;

import com.monkey.lang.token.Token;
import com.monkey.lang.token.TokenType;
import org.junit.jupiter.api.Test;

import static com.monkey.lang.token.TokenLiterals.LET;
import static com.monkey.lang.token.TokenLiterals.IDENT;
import static com.monkey.lang.token.TokenLiterals.ASSIGN;
import static com.monkey.lang.token.TokenLiterals.INT;
import static com.monkey.lang.token.TokenLiterals.SEMICOLON;
import static com.monkey.lang.token.TokenLiterals.FUNCTION;
import static com.monkey.lang.token.TokenLiterals.LPAREN;
import static com.monkey.lang.token.TokenLiterals.COMMA;
import static com.monkey.lang.token.TokenLiterals.RPAREN;
import static com.monkey.lang.token.TokenLiterals.LBRACE;
import static com.monkey.lang.token.TokenLiterals.PLUS;
import static com.monkey.lang.token.TokenLiterals.RBRACE;
import static com.monkey.lang.token.TokenLiterals.BANG;
import static com.monkey.lang.token.TokenLiterals.MINUS;
import static com.monkey.lang.token.TokenLiterals.SLASH;
import static com.monkey.lang.token.TokenLiterals.ASTERISK;
import static com.monkey.lang.token.TokenLiterals.LT;
import static com.monkey.lang.token.TokenLiterals.GT;

import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {

    @Test
    void nextToken() {
        //final String INPUT = "=+(){},;";
//        final String INPUT = "let five = 5;" +
//                "let ten = 10;" +
//                "\n" +
//                "let add = fn(x, y) {\n" +
//                "  x + y;\n" +
//                "};\n" +
//
//                "let result = add(five, ten);";

        final String INPUT = "let five = 5;\n" +
                "let ten = 10;\n" +
                "\n" +
                "let add = fn(x, y) {\n" +
                "  x + y;\n" +
                "};\n" +
                "\n" +
                "let result = add(five, ten);\n" +
                "!-/*5;\n" +
                "5 < 10 > 5;" +
                "if (5 < 10) {\n" +
                "    return true;\n" +
                "} else {" +
                "return false;" +
                "}";

        final Token[] tests = {
                new Token(new TokenType(LET), "let"),
                new Token(new TokenType(IDENT), "five"),
                new Token(new TokenType(ASSIGN), "="),
                new Token(new TokenType(INT), "5"),
                new Token(new TokenType(SEMICOLON), ";"),
                new Token(new TokenType(LET), "let"),
                new Token(new TokenType(IDENT), "ten"),
                new Token(new TokenType(ASSIGN), "="),
                new Token(new TokenType(INT), "10"),
                new Token(new TokenType(SEMICOLON), ";"),

                new Token(new TokenType(LET), "let"),
                new Token(new TokenType(IDENT), "add"),
                new Token(new TokenType(ASSIGN), "="),
                new Token(new TokenType(FUNCTION), "fn"),
                new Token(new TokenType(LPAREN), "("),
                new Token(new TokenType(IDENT), "x"),
                new Token(new TokenType(COMMA), ","),
                new Token(new TokenType(IDENT), "y"),
                new Token(new TokenType(RPAREN), ")"),
                new Token(new TokenType(LBRACE), "{"),
                new Token(new TokenType(IDENT), "x"),
                new Token(new TokenType(PLUS), "+"),
                new Token(new TokenType(IDENT), "y"),
                new Token(new TokenType(SEMICOLON), ";"),
                new Token(new TokenType(RBRACE), "}"),
                new Token(new TokenType(SEMICOLON), ";"),

                new Token(new TokenType(LET), "let"),
                new Token(new TokenType(IDENT), "result"),
                new Token(new TokenType(ASSIGN), "="),
                new Token(new TokenType(IDENT), "add"),
                new Token(new TokenType(LPAREN), "("),
                new Token(new TokenType(IDENT), "five"),
                new Token(new TokenType(COMMA), ","),
                new Token(new TokenType(IDENT), "ten"),
                new Token(new TokenType(RPAREN), ")"),
                new Token(new TokenType(SEMICOLON), ";"),

                new Token(new TokenType(BANG), "!"),
                new Token(new TokenType(MINUS), "-"),
                new Token(new TokenType(SLASH), "/"),
                new Token(new TokenType(ASTERISK), "*"),
                new Token(new TokenType(INT), "5"),
                new Token(new TokenType(SEMICOLON), ";"),

                new Token(new TokenType(INT), "5"),
                new Token(new TokenType(LT), "<"),
                new Token(new TokenType(INT), "10"),
                new Token(new TokenType(GT), ">"),
                new Token(new TokenType(INT), "5"),
                new Token(new TokenType(SEMICOLON), ";"),
                // new Token(new TokenType(TokenLiterals.EOF), ""),

        };

        /*
        input := `let five = 5;
let ten = 10;

let add = fn(x, y) {

  x + y;
};

let result = add(five, ten);
!-/*5;
5 < 10 > 5;
`
         */

        final Lexer lexer = Lexer.New(INPUT);

        for (final Token test : tests) {
            final Token tok = lexer.nextToken();
            assertEquals(tok.getType(), test.getType());
            assertEquals(tok.getLiteral(), test.getLiteral());
        }

    }
}