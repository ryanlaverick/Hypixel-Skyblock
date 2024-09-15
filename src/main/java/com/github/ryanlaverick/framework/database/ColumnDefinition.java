package com.github.ryanlaverick.framework.database;

public interface ColumnDefinition {
    String getColumn();

    String asSql();
}
