package com.monkey.lang.ast;

import com.monkey.lang.token.Token;

public final class LetStatement extends Statement implements Node {

    private Token token;
    private Identifier name;
    private Expression value;

    public Token getToken() {
        return token;
    }

    public void setToken(final Token token) {
        this.token = token;
    }

    public Identifier getName() {
        return name;
    }

    public void setName(final Identifier name) {
        this.name = name;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(final Expression value) {
        this.value = value;
    }

    @Override
    public String tokenLiteral() {
        return this.token.getLiteral();
    }

    // TODO: Analyze this ...
    @Override
    public void statementNode() {

    }

    @Override
    public String toString() {
        final StringBuilder out = new StringBuilder();

        out.append(this.tokenLiteral()).append(" ");
        out.append(this.name.toString());
        out.append(" = ");
        if(this.value != null) {
            out.append(value.toString());
        }
        out.append(";");

        return out.toString();
    }
}