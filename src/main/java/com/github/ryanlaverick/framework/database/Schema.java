package com.github.ryanlaverick.framework.database;

import java.util.ArrayList;
import java.util.List;

public class Schema {
    private String table;
    private final List<ColumnDefinition> columnDefinitions;

    public Schema() {
        this.columnDefinitions = new ArrayList<>();
    }

    public Schema table(String table) {
        this.table = table;

        return this;
    }

    public String getTable() {
        return table;
    }

    public Schema addColumnDefinition(ColumnDefinition columnDefinition) {
        this.columnDefinitions.add(columnDefinition);

        return this;
    }

    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    public String asSql() {
        String tableString = "CREATE TABLE IF NOT EXISTS ':table'";
        tableString = tableString.replace(":table", this.table);

        if (!this.getColumnDefinitions().isEmpty()) {
            tableString = tableString.concat(" (:columns)");
            String columnString = "";

            for (ColumnDefinition columnDefinition : this.getColumnDefinitions()) {
                // Don't add trailing comma to last column
                if (this.getColumnDefinitions().indexOf(columnDefinition) == this.getColumnDefinitions().size()) {
                    columnString = columnString.concat(columnDefinition.asSql());
                } else {
                    columnString = columnString.concat(columnDefinition.asSql().concat(", "));
                }
            }

            tableString = tableString.replace(":columns", columnString);
        }

        return tableString;
    }
}
