package com.monkey.lang.lexer;

import com.monkey.lang.token.Token;
import com.monkey.lang.token.TokenType;
import org.junit.jupiter.api.Test;

import static com.monkey.lang.token.TokenLiterals.*;
import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {

    @Test
    void nextToken() {

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
                "}" +

                "10 == 10;\n" +
                "10 != 9;\n" +
                "\"foobar\"\n" +
                "\"foo bar\"\n" +
                "[1, 2];\n" +
                "{\"foo\": \"bar\"}\n" +
                "me;\n" +
                "tr;\n" +
                "word;\n" +
                "cpt;\n" +
                "ref;";

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

                new Token(new TokenType(IF), "if"),
                new Token(new TokenType(LPAREN), "("),
                new Token(new TokenType(INT), "5"),
                new Token(new TokenType(LT), "<"),
                new Token(new TokenType(INT), "10"),
                new Token(new TokenType(RPAREN), ")"),
                new Token(new TokenType(LBRACE), "{"),
                new Token(new TokenType(RETURN), "return"),
                new Token(new TokenType(TRUE), "true"),
                new Token(new TokenType(SEMICOLON), ";"),
                new Token(new TokenType(RBRACE), "}"),
                new Token(new TokenType(ELSE), "else"),
                new Token(new TokenType(LBRACE), "{"),
                new Token(new TokenType(RETURN), "return"),
                new Token(new TokenType(FALSE), "false"),
                new Token(new TokenType(SEMICOLON), ";"),
                new Token(new TokenType(RBRACE), "}"),


                new Token(new TokenType(INT), "10"),
                new Token(new TokenType(EQ), "=="),
                new Token(new TokenType(INT), "10"),
                new Token(new TokenType(SEMICOLON), ";"),

                new Token(new TokenType(INT), "10"),
                new Token(new TokenType(NOT_EQ), "!="),
                new Token(new TokenType(INT), "9"),
                new Token(new TokenType(SEMICOLON), ";"),

                new Token(new TokenType(STRING), "foobar"),
                new Token(new TokenType(STRING), "foo bar"),
                new Token(new TokenType(LBRACKET), "["),

                new Token(new TokenType(INT), "1"),
                new Token(new TokenType(COMMA), ","),
                new Token(new TokenType(INT), "2"),
                new Token(new TokenType(RBRACKET), "]"),
                new Token(new TokenType(SEMICOLON), ";"),

                new Token(new TokenType(LBRACE), "{"),
                new Token(new TokenType(STRING), "foo"),
                new Token(new TokenType(COLON), ":"),
                new Token(new TokenType(STRING), "bar"),
                new Token(new TokenType(RBRACE), "}"),

                new Token(new TokenType(ME), "me"),
                new Token(new TokenType(SEMICOLON), ";"),

                new Token(new TokenType(TR), "tr"),
                new Token(new TokenType(SEMICOLON), ";"),

                new Token(new TokenType(WORD), "word"),
                new Token(new TokenType(SEMICOLON), ";"),

                new Token(new TokenType(CPT), "cpt"),
                new Token(new TokenType(SEMICOLON), ";"),

                new Token(new TokenType(REF), "ref"),
                new Token(new TokenType(SEMICOLON), ";"),

                new Token(new TokenType(EOF), ""),

        };

        final Lexer lexer = Lexer.New(INPUT);

        for (final Token test : tests) {
            final Token tok = lexer.nextToken();
            assertEquals(tok.getType(), test.getType());
            assertEquals(tok.getLiteral(), test.getLiteral());
        }

    }

    @Test
    void testLineNumbers() {
        final String INPUT = "\n" +
                "\t\tlet five = 5;\n" +
                "\t\n" +
                "\t\n" +
                "\t\tlet ten = 10;\n" +
                "\t\n" +
                "\t\n";

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

        };

        final Lexer lexer = Lexer.New(INPUT);

        for (final Token test : tests) {
            final Token tok = lexer.nextToken();
            assertEquals(tok.getType(), test.getType());
            assertEquals(tok.getLiteral(), test.getLiteral());
        }

        final int inputLineNumberCount = INPUT.split("\n").length;
        assertEquals(inputLineNumberCount, lexer.currentLineNumber());
    }

}