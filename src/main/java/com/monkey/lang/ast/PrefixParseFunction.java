package com.monkey.lang.ast;

import jdk.nashorn.internal.objects.annotations.Function;

@FunctionalInterface
public interface PrefixParseFunction {
    Expression function();
}
