package com.monkey.lang;

import com.monkey.lang.ast.Expression;

@FunctionalInterface
public interface InfixParseFunction {
    Expression function(Expression expr);
}
