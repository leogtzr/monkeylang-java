package com.monkey.lang.token;

public class Token {

    private TokenType type;
    private String literal;

    public Token(final TokenType type, final String literal) {
        this.type = type;
        this.literal = literal;
    }

    public Token() {}

    public TokenType getType() {
        return type;
    }

    public void setType(final TokenType type) {
        this.type = type;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(final String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", literal='" + literal + '\'' +
                '}';
    }
}