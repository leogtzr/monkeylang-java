package com.monkey.lang.ast;

import java.util.ArrayList;
import java.util.List;

public final class Program implements Node {

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

    @Override
    public String toString() {
        final StringBuilder out = new StringBuilder();
        for (final Statement stmt : this.statements) {
            out.append(stmt.toString());
        }
        return out.toString();
    }

    @Override
    public String tokenLiteral() {
        if (this.statements.size() > 0) {
            return statements.get(0).tokenLiteral();
        } else {
            return "";
        }
    }
}