package com.monkey.lang.ast;

import java.util.List;

public final class Program {

    private List<Statement> statements;

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(final List<Statement> statements) {
        this.statements = statements;
    }

}