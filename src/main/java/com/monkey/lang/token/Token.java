package com.monkey.lang.token;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Token {

    private TokenType type;
    private String literal;

    public Token(final TokenType type, final String literal) {
        this.type = type;
        this.literal = literal;
    }

    public Token() {}

    private static Map<String, String> keywords = new HashMap<String, String>() {{
        put("fn", TokenLiterals.FUNCTION);
        put("let", TokenLiterals.LET);
        put("true", TokenLiterals.TRUE);
        put("false", TokenLiterals.FALSE);
        put("if", TokenLiterals.IF);
        put("else", TokenLiterals.ELSE);
        put("return", TokenLiterals.RETURN);
    }};

    public static TokenType lookupIdentifier(final String literal) {
        if (keywords.containsKey(literal)) {
            return new TokenType(keywords.get(literal));
        } else {
            return new TokenType(TokenLiterals.IDENT);
        }
    }

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
        return "Token{" + type + ", literal='" + literal + '\'' + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Token token = (Token) o;
        return Objects.equals(type, token.type) && Objects.equals(literal, token.literal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, literal);
    }
}