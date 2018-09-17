package com.monkey.lang.parser;

import com.monkey.lang.ast.Expression;
import com.monkey.lang.ast.Node;
import com.monkey.lang.ast.Statement;
import com.monkey.lang.token.Token;

public final class ReturnStatement extends Statement implements Node {

    private Token token;
    private Expression returnValue;

    public Token getToken() {
        return token;
    }

    public void setToken(final Token token) {
        this.token = token;
    }

    public Expression getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(final Expression returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public void statementNode() {
        // Empty for now ...
    }

    @Override
    public String tokenLiteral() {
        return this.token.getLiteral();
    }

}