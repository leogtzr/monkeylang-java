package com.monkey.lang.repl;

import com.monkey.lang.lexer.Lexer;
import com.monkey.lang.token.Token;
import com.monkey.lang.token.TokenLiterals;
import com.monkey.lang.token.TokenType;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public final class ReadEvalPrintLoop {

    public static final String PROMPT = "$ ";

    public static void start(final InputStream in, final PrintStream out) {

        final Scanner scanner = new Scanner(in);
        do {
            out.print(PROMPT);
            final String line = scanner.nextLine();
            final Lexer lexer = Lexer.New(line);
            for (Token tok = lexer.nextToken(); ! tok.getType().equals(new TokenType(TokenLiterals.EOF));
                 tok = lexer.nextToken()) {
                out.println(tok);
            }
        } while (scanner.hasNext());
    }

}