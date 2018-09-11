package com.monkey.lang.lexer;

import com.monkey.lang.token.Token;
import com.monkey.lang.token.TokenLiterals;
import com.monkey.lang.token.TokenType;
import org.junit.jupiter.api.Test;

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
                "5 < 10 > 5;";

        final Token[] tests = {
                new Token(new TokenType(TokenLiterals.LET), "let"),
                new Token(new TokenType(TokenLiterals.IDENT), "five"),
                new Token(new TokenType(TokenLiterals.ASSIGN), "="),
                new Token(new TokenType(TokenLiterals.INT), "5"),
                new Token(new TokenType(TokenLiterals.SEMICOLON), ";"),
                new Token(new TokenType(TokenLiterals.LET), "let"),
                new Token(new TokenType(TokenLiterals.IDENT), "ten"),
                new Token(new TokenType(TokenLiterals.ASSIGN), "="),
                new Token(new TokenType(TokenLiterals.INT), "10"),
                new Token(new TokenType(TokenLiterals.SEMICOLON), ";"),

                new Token(new TokenType(TokenLiterals.LET), "let"),
                new Token(new TokenType(TokenLiterals.IDENT), "add"),
                new Token(new TokenType(TokenLiterals.ASSIGN), "="),
                new Token(new TokenType(TokenLiterals.FUNCTION), "fn"),
                new Token(new TokenType(TokenLiterals.LPAREN), "("),
                new Token(new TokenType(TokenLiterals.IDENT), "x"),
                new Token(new TokenType(TokenLiterals.COMMA), ","),
                new Token(new TokenType(TokenLiterals.IDENT), "y"),
                new Token(new TokenType(TokenLiterals.RPAREN), ")"),
                new Token(new TokenType(TokenLiterals.LBRACE), "{"),
                new Token(new TokenType(TokenLiterals.IDENT), "x"),
                new Token(new TokenType(TokenLiterals.PLUS), "+"),
                new Token(new TokenType(TokenLiterals.IDENT), "y"),
                new Token(new TokenType(TokenLiterals.SEMICOLON), ";"),
                new Token(new TokenType(TokenLiterals.RBRACE), "}"),
                new Token(new TokenType(TokenLiterals.SEMICOLON), ";"),

                new Token(new TokenType(TokenLiterals.LET), "let"),
                new Token(new TokenType(TokenLiterals.IDENT), "result"),
                new Token(new TokenType(TokenLiterals.ASSIGN), "="),
                new Token(new TokenType(TokenLiterals.IDENT), "add"),
                new Token(new TokenType(TokenLiterals.LPAREN), "("),
                new Token(new TokenType(TokenLiterals.IDENT), "five"),
                new Token(new TokenType(TokenLiterals.COMMA), ","),
                new Token(new TokenType(TokenLiterals.IDENT), "ten"),
                new Token(new TokenType(TokenLiterals.RPAREN), ")"),
                new Token(new TokenType(TokenLiterals.SEMICOLON), ";"),

                new Token(new TokenType(TokenLiterals.BANG), "!"),
                new Token(new TokenType(TokenLiterals.MINUS), "-"),
                new Token(new TokenType(TokenLiterals.SLASH), "/"),
                new Token(new TokenType(TokenLiterals.ASTERISK), "*"),
                new Token(new TokenType(TokenLiterals.INT), "5"),
                new Token(new TokenType(TokenLiterals.SEMICOLON), ";"),

                new Token(new TokenType(TokenLiterals.INT), "5"),
                new Token(new TokenType(TokenLiterals.LT), "<"),
                new Token(new TokenType(TokenLiterals.INT), "10"),
                new Token(new TokenType(TokenLiterals.GT), ">"),
                new Token(new TokenType(TokenLiterals.INT), "5"),
                new Token(new TokenType(TokenLiterals.SEMICOLON), ";"),
                new Token(new TokenType(TokenLiterals.EOF), ""),
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
            Token tok = lexer.nextToken();
            assertEquals(tok.getType(), test.getType());
            assertEquals(tok.getLiteral(), test.getLiteral());
        }

    }
}