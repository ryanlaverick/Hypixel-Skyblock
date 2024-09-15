package com.github.ryanlaverick.framework.database;

public final class DropColumnDefinition implements ColumnDefinition {
    private final String column;

    public DropColumnDefinition(String column) {
        this.column = column;
    }

    @Override
    public String getColumn() {
        return column;
    }

    @Override
    public String asSql() {
        return "DROP COLUMN `:column`".replace(":column", this.column);
    }
}
