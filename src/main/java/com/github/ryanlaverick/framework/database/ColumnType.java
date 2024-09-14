package com.github.ryanlaverick.framework.database;

public enum ColumnType {
    VARCHAR("VARCHAR"),
    INT("INT"),
    BIGINT("BIGINT"),
    BOOLEAN("BOOLEAN"),
    FLOAT("FLOAT"),
    DOUBLE("DOUBLE"),
    DATE("DATE"),
    DATETIME("DATETIME"),
    TIMESTAMP("TIMESTAMP");

    final String sql;

    ColumnType(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
