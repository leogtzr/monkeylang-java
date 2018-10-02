package com.monkey.lang.parser;

import com.monkey.lang.ast.*;
import com.monkey.lang.lexer.Lexer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testLetStatements() {
        final String input = "" +
	"let x = 5;\n" +
	"let y = 10;\n" +
	"let foobar = 838383;";

        final Lexer l = Lexer.New(input);
        final Parser p = new Parser(l);

        final Program program = p.parseProgram();

        checkParserErrors(p.getErrors());
//        if (p.getErrors().size() > 0) {
//            System.err.printf("parser has %d errors\n", p.getErrors().size());
//            for (final String msg : p.getErrors()) {
//                System.err.printf("parser error: %s", msg);
//            }
//            fail(String.format("parser has %d errors", p.getErrors().size()));
//        }

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
        // assertEquals(s instanceof LetStatement, String.format("s not *ast.LetStatement. got=%s", s));

        final LetStatement letStmt = (LetStatement)s;
        assertEquals(letStmt.getName().tokenLiteral(), name, String.format("s.Name not '%s'. got=%s", name, letStmt.getName()));
    }

    private void checkParserErrors(final List<String> errors) {
        if (errors.size() > 0) {
            System.err.printf("parser has %d errors\n", errors.size());
            for (final String msg : errors) {
                System.err.printf("parser error: %s", msg);
            }
            fail(String.format("parser has %d errors", errors.size()));
        }
    }

    @Test
    public void testReturnStatements() {
        final String input = "return 5;\n" +
"return 10;\n" +
"return 993322;\n";

        final Lexer l = Lexer.New(input);
        final Parser p = new Parser(l);

        final Program program = p.parseProgram();
        checkParserErrors(p.getErrors());

        assertEquals(program.getStatements().size(), 3, String.format("program.Statements does not contain 3 statements. got=%d",
                program.getStatements().size()));

        for (final Statement stmt : program.getStatements()) {
            assertTrue(stmt instanceof ReturnStatement, String.format("stmt not *ast.returnStatement. got=[%s]", stmt));
            final ReturnStatement returnStmt = (ReturnStatement) stmt;
            assertEquals(returnStmt.tokenLiteral(), "return",
                    String.format("returnStmt.TokenLiteral not 'return', got '%s'",
                            returnStmt.tokenLiteral()));
        }
    }

    @Test
    public void testIdentifierExpression() {
        final String input = "foobar;";

        final Lexer l = Lexer.New(input);
        final Parser p = new Parser(l);

        final Program program = p.parseProgram();

        assertEquals(program.getStatements().size(), 1, String.format("program has not enough statements. got=%d",
                program.getStatements().size()));

        final ExpressionStatement firstStmt = (ExpressionStatement) program.getStatements().get(0);
        assertTrue(
                firstStmt instanceof ExpressionStatement
                , String.format("program.Statements[0] is not ast.ExpressionStatement. got=%s"
                , firstStmt
                )
        );

        final Identifier ident = (Identifier) firstStmt.getExpression();

        assertEquals(ident.getValue(), "foobar", String.format("ident.Value not %s. got=%s", "foobar", ident.getValue()));
        assertEquals(ident.tokenLiteral(), "foobar", String.format("ident.TokenLiteral not %s. got=%s", "foobar", ident.tokenLiteral()));
    }

}