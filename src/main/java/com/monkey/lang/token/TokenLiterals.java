package com.monkey.lang.token;

public class TokenLiterals {

    public static final String LBRACKET = "[";
    public static final String RBRACKET = "[";

    private TokenLiterals() {}

    public static final String ILLEGAL = "ILLEGAL";
    public static final String EOF = "EOF";

    // IDENT ... Identifiers + literals
    public static final String IDENT = "IDENT"; // add, foobar, x, y, ...
    public static final String INT   = "INT";   // 213789

    // Operators
    public static final String ASSIGN = "=";
    public static final String PLUS   = "+";

    // Delimiters
    public static final String COMMA     = ",";
    public static final String SEMICOLON = ";";
    public static final String COLON = ":";

    public static final String LPAREN = "(";
    public static final String RPAREN = ")";
    public static final String LBRACE = "{";
    public static final String RBRACE = "}";

    // Keywords
    public static final String FUNCTION = "FUNCTION";
    public static final String LET      = "LET";
    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";
    public static final String IF = "IF";
    public static final String ELSE = "ELSE";
    public static final String RETURN = "RETURN";

    public static final String WORD = "WORD";
    public static final String REF = "REF";
    public static final String CPT = "CPT";
    public static final String TR = "TR";
    public static final String ME = "ME";

    // Operators
    public static final String MINUS = "-";
    public static final String BANG = "!";
    public static final String ASTERISK = "*";
    public static final String SLASH = "/";
    public static final String LT = "<";
    public static final String GT = ">";

    public static final String EQ = "==";
    public static final String NOT_EQ = "!=";

    public static final String STRING = "STRING";
}