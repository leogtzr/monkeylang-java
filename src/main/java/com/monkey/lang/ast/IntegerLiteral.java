package com.monkey.lang.ast;

import com.monkey.lang.token.Token;

public class IntegerLiteral extends Expression implements Node {

    private Token token;
    private int value;

    public IntegerLiteral(final Token token, final int value) {
        this.token = token;
        this.value = value;
    }

    public IntegerLiteral() { }

    @Override
    public void expressionNode() {}

    @Override
    public String tokenLiteral() {
        return null;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(final Token token) {
        this.token = token;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.token.getLiteral();
    }
}
