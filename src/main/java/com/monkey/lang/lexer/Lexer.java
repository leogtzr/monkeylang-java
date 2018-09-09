package com.monkey.lang.lexer;

import com.monkey.lang.token.Token;
import com.monkey.lang.token.TokenLiterals;
import com.monkey.lang.token.TokenType;

public class Lexer {

    private String input;
    private int position;
    private int readPosition;
    private char ch;

    public static Lexer New(final String input) {
        final Lexer lexer = new Lexer();
        lexer.setInput(input);
        lexer.readChar();
        return lexer;
    }

    public String getInput() {
        return input;
    }

    public void setInput(final String input) {
        this.input = input;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(final int position) {
        this.position = position;
    }

    public int getReadPosition() {
        return readPosition;
    }

    public void setReadPosition(final int readPosition) {
        this.readPosition = readPosition;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(final char ch) {
        this.ch = ch;
    }

    @Override
    public String toString() {
        return "Lexer{" +
                "input='" + input + '\'' +
                ", position=" + position +
                ", readPosition=" + readPosition +
                ", ch=" + ch +
                '}';
    }

    private void readChar() {
        if (this.readPosition >= input.length()) {
            this.ch = (char)0;
        } else {
            this.ch = this.input.charAt(this.readPosition);
        }
        this.position = this.readPosition;
        this.readPosition++;
    }

    public Token nextToken() {
        Token tok = null;

        switch (this.ch) {
            case '=':
                tok = newToken(new TokenType(TokenLiterals.ASSIGN), this.ch);
                break;

            case ';':
                tok = newToken(new TokenType(TokenLiterals.SEMICOLON), this.ch);
                break;

            case '(':
                tok = newToken(new TokenType(TokenLiterals.LPAREN), this.ch);
                break;

            case ')':
                tok = newToken(new TokenType(TokenLiterals.RPAREN), this.ch);
                break;

            case ',':
                tok = newToken(new TokenType(TokenLiterals.COMMA), this.ch);
                break;

            case '+':
                tok = newToken(new TokenType(TokenLiterals.PLUS), this.ch);
                break;

            case '{':
                tok = newToken(new TokenType(TokenLiterals.LBRACE), this.ch);
                break;

            case '}':
                tok = newToken(new TokenType(TokenLiterals.RBRACE), this.ch);
                break;

            case 0:
                tok = newToken(new TokenType(TokenLiterals.EOF), (char)0);
                break;
        }

        this.readChar();
        return tok;
    }

    private Token newToken(final TokenType tokenType, final char ch) {
        return new Token(tokenType, ch + "");
    }

}