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
            fail("ParseProgram() returned nil");
        }
        if (program.getStatements().size() != 3) {
            fail(String.format("program.Statements does not contain 3 statements. got=%d", program.getStatements().size()));

        }

        /*tests := []struct {
            expectedIdentifier string
        }{
            {"x"},
            {"y"},
            {"foobar"},
        }*/
        final String[] tests = {"x", "y", "foobar"};

        for (int i = 0; i < tests.length; i++) {
            final Statement stmt = program.getStatements().get(i);
            if (!testLetStatement(stmt, tests[i])) {
                // TODO: Do something here ...
                return;
            }
        }

    }

    private boolean testLetStatement(final Statement s, final String name) {
        if (!s.tokenLiteral().equals("let")) {
            //t.Errorf("s.TokenLiteral() not 'let', got=%q", s.TokenLiteral());
            fail(String.format("s.TokenLiteral() not 'let', got=%s", s.tokenLiteral()));
            return false;
        }

        if (!(s instanceof LetStatement)) {
            fail(String.format("s not *ast.LetStatement. got=%s", s));
        }
//        letStmt, ok := s.(*ast.LetStatement);
//        if !ok {
//            t.Errorf("s not *ast.LetStatement. got=%q", s)
//            return false
//        }

        final LetStatement letStmt = (LetStatement)s;

        if (!letStmt.getName().tokenLiteral().equals(name)) {
            //t.Errorf("s.Name not '%s'. got=%q", name, letStmt.Name);
            fail(String.format("s.Name not '%s'. got=%s", name, letStmt.getName()));
            return false;
        }

        return true;
    }

}