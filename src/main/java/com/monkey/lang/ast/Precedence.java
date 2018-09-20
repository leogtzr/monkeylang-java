package com.monkey.lang.ast;

public final class Precedence {

    /*
    _ int = iota
	LOWEST
	EQUALS      // ==
	LESSGREATER // > or <
	SUM         // +
	PRODUCT     // *
	PREFIX      // -X or !X
	CALL        // myFunction(X)
     */
    public static final int LOWEST = 0;
    public static final int EQUALS = 1;
    public static final int LESSGREATER = 2;
    public static final int SUM = 3;
    public static final int PRODUCT = 4;
    public static final int PREFIX = 5;
    public static final int CALL = 6;

    private Precedence() {}

}