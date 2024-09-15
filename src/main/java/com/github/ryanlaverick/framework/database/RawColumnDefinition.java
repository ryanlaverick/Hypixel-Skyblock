package com.github.ryanlaverick.framework.database;

public final class RawColumnDefinition implements ColumnDefinition {
    private final String raw;

    public RawColumnDefinition(String raw) {
        this.raw = raw;
    }

    @Override
    public String getColumn() {
        return "";
    }

    @Override
    public String asSql() {
        return raw;
    }
}
