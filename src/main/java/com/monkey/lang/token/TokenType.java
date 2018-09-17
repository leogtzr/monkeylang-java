package com.monkey.lang.token;

import java.util.Objects;

public final class TokenType {

    private String type;

    public TokenType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        //return String.format("T(%s)", this.type);
        return this.type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TokenType tokenType = (TokenType) o;
        return Objects.equals(type, tokenType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}