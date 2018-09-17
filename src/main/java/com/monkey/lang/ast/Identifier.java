package com.monkey.lang.ast;

import com.monkey.lang.token.Token;

import java.util.Objects;

public final class Identifier extends Expression implements Node {

    private Token token;
    private String value;

    public Token getToken() {
        return token;
    }

    public void setToken(final Token token) {
        this.token = token;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Identifier that = (Identifier) o;
        return Objects.equals(token, that.token) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, value);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public String tokenLiteral() {
        return this.token.getLiteral();
    }

    @Override
    public void expressionNode() {

    }
}