package com.monkey.lang.parser;

import com.monkey.lang.ast.LetStatement;
import com.monkey.lang.ast.Program;
import com.monkey.lang.ast.Statement;
import com.monkey.lang.lexer.Lexer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testLetStatements() {
        final String input = "" +
	"let x = 5;\n" +
	"let y = 10;\n" +
	"let foobar = 838383;";

        final Lexer l = Lexer.New(input);
        final Parser p = Parser.New(l);

        final Program program = p.parseProgram();

        if (p.getErrors().size() > 0) {
            System.err.printf("parser has %d errors\n", p.getErrors().size());
            for (final String msg : p.getErrors()) {
                System.err.printf("parser error: %s", msg);
            }
            fail(String.format("parser has %d errors", p.getErrors().size()));
        }

        if (program == null) {
            fail("parseProgram() returned null");
        }

        assertEquals(
                program.getStatements().size(),
                3,
                String.format("program.Statements does not contain 3 statements. got=%d", program.getStatements().size()));

        final String[] tests = {"x", "y", "foobar"};

        for (int i = 0; i < tests.length; i++) {
            final Statement stmt = program.getStatements().get(i);
            testLetStatement(stmt, tests[i]);
        }

    }

    private void testLetStatement(final Statement s, final String name) {
        assertEquals(s.tokenLiteral(), "let", String.format("s.TokenLiteral() not 'let', got=%s", s.tokenLiteral()));
        assertEquals(s instanceof LetStatement, true, String.format("s not *ast.LetStatement. got=%s", s));

        final LetStatement letStmt = (LetStatement)s;
        assertEquals(letStmt.getName().tokenLiteral(), name, String.format("s.Name not '%s'. got=%s", name, letStmt.getName()));
    }

}