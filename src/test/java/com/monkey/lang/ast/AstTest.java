package com.monkey.lang.ast;

import com.monkey.lang.token.Token;
import com.monkey.lang.token.TokenLiterals;
import com.monkey.lang.token.TokenType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AstTest {

    @Test
    public void testString() {

        final LetStatement stmt = new LetStatement();
        stmt.setToken(new Token(new TokenType(TokenLiterals.LET), "let"));

        final Identifier identifierMyVar = new Identifier();
        identifierMyVar.setToken(new Token(new TokenType(TokenLiterals.IDENT), "myVar"));
        identifierMyVar.setValue("myVar");

        stmt.setName(identifierMyVar);

        final Identifier identifierAnotherVar = new Identifier();
        identifierAnotherVar.setToken(new Token(new TokenType(TokenLiterals.IDENT), "anotherVar"));
        identifierAnotherVar.setValue("anotherVar");

        stmt.setValue(identifierAnotherVar);

        final Program program = new Program();
        program.addStatement(stmt);

        final String programString = program.toString();
	    final String expectedProgramString = "let myVar = anotherVar;";

        assertEquals(programString, expectedProgramString);
    }


}