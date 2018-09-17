package com.monkey.lang.ast;

import java.util.ArrayList;
import java.util.List;

public final class Program {

    private List<Statement> statements = new ArrayList<>();

    public List<Statement> getStatements() {
        return statements;
    }

    public void addStatement(final Statement stmt) {
        this.statements.add(stmt);
    }

    public void setStatements(final List<Statement> statements) {
        this.statements = statements;
    }

}