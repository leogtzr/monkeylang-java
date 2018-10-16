package com.monkey.lang.ast;

import com.monkey.lang.token.Token;

public class Bool extends Expression implements Node {

    private Token token;
    private boolean value;

    public Bool(final Token token, final boolean value) {
        this.token = token;
        this.value = value;
    }

    public Bool() {
    }

    @Override
    public void expressionNode() {
    }

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

    public boolean getValue() {
        return value;
    }

    public void setValue(final boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.token.getLiteral();
    }
}