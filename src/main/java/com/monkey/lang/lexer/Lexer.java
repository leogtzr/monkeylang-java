package com.monkey.lang.lexer;

import com.monkey.lang.token.Token;
import com.monkey.lang.token.TokenLiterals;
import com.monkey.lang.token.TokenType;

public final class Lexer {

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

        this.skipWithespace();

        switch (this.ch) {
            case '=':
                if (this.peekChar() == '=') {
                    final char ch = this.ch;
                    this.readChar();
                    tok = new Token(new TokenType(TokenLiterals.EQ), String.valueOf(ch) + String.valueOf(this.ch));
                } else {
                    tok = newToken(new TokenType(TokenLiterals.ASSIGN), this.ch);
                }
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

            case '-':
                tok = newToken(new TokenType(TokenLiterals.MINUS), this.ch);
                break;

            case '!':
                if (this.peekChar() == '=') {
                    final char ch = this.ch;
                    this.readChar();
                    tok = new Token(new TokenType(TokenLiterals.NOT_EQ), String.valueOf(ch) + String.valueOf(this.ch));
                } else {
                    tok = newToken(new TokenType(TokenLiterals.BANG), this.ch);
                }
                break;

            case '/':
                tok = newToken(new TokenType(TokenLiterals.SLASH), this.ch);
                break;

            case '*':
                tok = newToken(new TokenType(TokenLiterals.ASTERISK), this.ch);
                break;

            case '<':
                tok = newToken(new TokenType(TokenLiterals.LT), this.ch);
                break;

            case '>':
                tok = newToken(new TokenType(TokenLiterals.GT), this.ch);
                break;

            case '{':
                tok = newToken(new TokenType(TokenLiterals.LBRACE), this.ch);
                break;

            case '}':
                tok = newToken(new TokenType(TokenLiterals.RBRACE), this.ch);
                break;

            case '"':
                tok = new Token();
                tok.setType(new TokenType(TokenLiterals.STRING));
                tok.setLiteral(this.readString());
                break;

            case '[':
                tok = newToken(new TokenType(TokenLiterals.LBRACKET), this.ch);
                break;

            case ':':
                tok = newToken(new TokenType(TokenLiterals.COLON), this.ch);
                break;

            case ']':
                tok = newToken(new TokenType(TokenLiterals.RBRACKET), this.ch);
                break;

            case 0:
                tok = newToken(new TokenType(TokenLiterals.EOF), (char)0);
                tok.setLiteral("");
                break;

            default:
                if (isLetter(this.ch)) {
                    tok = new Token();
                    tok.setLiteral(this.readIdentifier());
                    tok.setType(Token.lookupIdentifier(tok.getLiteral()));
                    return tok;
                } else if (isDigit(this.ch)) {
                    tok = new Token();
                    tok.setLiteral(this.readNumber());
                    tok.setType(new TokenType(TokenLiterals.INT));
                    return tok;
                } else {
                    tok = newToken(new TokenType(TokenLiterals.ILLEGAL), this.ch);
                }

        }

        this.readChar();
        return tok;
    }

    private String readString() {
        final int pos = this.position + 1;

        for (;;) {
            this.readChar();
            if (this.ch == '"') {
                break;
            }
        }
        return this.input.substring(pos, this.position);
    }

    private static boolean isLetter(final char ch) {
        return 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z' || ch == '_';
    }

    private static boolean isDigit(final char ch) {
        return '0' <= ch && ch <= '9';
    }

    private void skipWithespace() {
        while (this.ch == ' ' || this.ch == '\t' || this.ch == '\r' || this.ch == '\n') {
            this.readChar();
        }
    }

    private String readIdentifier() {
        final int position = this.position;
        while (isLetter(this.ch)) {
            this.readChar();
        }
        return this.input.substring(position, this.position);
    }

    private String readNumber() {
        final int position = this.position;
        while (isDigit(this.ch)) {
            this.readChar();
        }
        return this.input.substring(position, this.position);
    }

    private char peekChar() {
        if (this.readPosition >= this.input.length()) {
            return 0;
        } else {
            return this.input.charAt(this.readPosition);
        }
    }

    private Token newToken(final TokenType tokenType, final char ch) {
        return new Token(tokenType, ch + "");
    }

}