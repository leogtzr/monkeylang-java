package com.monkey.lang.ast;

import com.monkey.lang.token.Token;

public class ExpressionStatement extends Statement implements Node {

    private Expression expression;
    private Token token;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(final Expression expression) {
        this.expression = expression;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(final Token token) {
        this.token = token;
    }

    @Override
    public void statementNode() {
        // Nothing for now ...
    }

    @Override
    public String tokenLiteral() {
        return this.token.getLiteral();
    }

    @Override
    public String toString() {
        if (this.expression != null) {
            return this.expression.toString();
        }
        return "";
    }
}
