package com.github.ryanlaverick.framework.database;

import java.util.ArrayList;
import java.util.List;

public final class Schema {
    private final String table;
    private final List<ColumnDefinition> columnDefinitions;

    private boolean isCreating = true;
    private boolean isDropping = false;
    private boolean isAltering = false;

    public Schema(String table) {
        this.table = table;
        this.columnDefinitions = new ArrayList<>();
    }

    public Schema drop() {
        this.isCreating = false;
        this.isDropping = true;
        this.isAltering = false;

        return this;
    }

    public Schema alter() {
        this.isCreating = false;
        this.isDropping = false;
        this.isAltering = true;

        return this;
    }

    public Schema create() {
        this.isCreating = true;
        this.isDropping = false;
        this.isAltering = false;

        return this;
    }

    public String getTable() {
        return table;
    }

    public boolean isCreating() {
        return isCreating;
    }

    public boolean isDropping() {
        return isDropping;
    }

    public boolean isAltering() {
        return isAltering;
    }

    public Schema addColumnDefinition(ColumnDefinition columnDefinition) {
        this.columnDefinitions.add(columnDefinition);

        return this;
    }

    public Schema primaryUuid(String column) {
        this.columnDefinitions.add(new CreateColumnDefinition(column, ColumnType.VARCHAR).length(36).primaryKey());

        return this;
    }

    public Schema foreignUuid(String column, String referencesTable, String referencesColumn) {
        this.columnDefinitions.add(new RawColumnDefinition(
                "`:column` VARCHAR(36) NOT NULL, FOREIGN KEY (`:column`) REFERENCES `:referenceTable`(`:referenceColumn`)"
                        .replace(":column", column)
                        .replace(":referenceTable", referencesTable)
                        .replace(":referenceColumn", referencesColumn)
        ));

        return this;
    }

    public Schema timestamp(String column) {
        this.addColumnDefinition(new CreateColumnDefinition(column, ColumnType.BIGINT).length(20));

        return this;
    }

    public Schema nullableTimestamp(String column) {
        this.columnDefinitions.add(new CreateColumnDefinition(column, ColumnType.BIGINT).length(20).nullable());

        return this;
    }

    public Schema string(String column) {
        this.columnDefinitions.add(new CreateColumnDefinition(column, ColumnType.VARCHAR));

        return this;
    }

    public Schema nullableString(String column) {
        this.columnDefinitions.add(new CreateColumnDefinition(column, ColumnType.VARCHAR).nullable());

        return this;
    }

    public Schema string(String column, int length) {
        this.columnDefinitions.add(new CreateColumnDefinition(column, ColumnType.VARCHAR).length(length));

        return this;
    }

    public Schema nullableString(String column, int length) {
        this.columnDefinitions.add(new CreateColumnDefinition(column, ColumnType.VARCHAR).length(length).nullable());

        return this;
    }

    public Schema raw(String raw) {
        this.addColumnDefinition(new RawColumnDefinition(raw));

        return this;
    }

    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    public String asSql() {
        if (this.isCreating()) {
            return this.getCreateSql();
        }

        if (this.isDropping()) {
            return this.getDropSql();
        }

        return this.getAlterSql();
    }

    private String getCreateSql() {
        String tableString = "CREATE TABLE IF NOT EXISTS `:table`"
                .replace(":table", this.getTable());

        if (!this.getColumnDefinitions().isEmpty()) {
            tableString = tableString.concat(" (:columns)");
            String columnString = "";

            for (ColumnDefinition columnDefinition : this.getColumnDefinitions()) {
                // Don't add trailing comma to last column
                if (this.getColumnDefinitions().indexOf(columnDefinition) == this.getColumnDefinitions().size() - 1) {
                    columnString = columnString.concat(columnDefinition.asSql());
                } else {
                    columnString = columnString.concat(columnDefinition.asSql().concat(", "));
                }
            }

            tableString = tableString.replace(":columns", columnString);
        }

        return tableString;
    }

    private String getAlterSql() {
        String tableString = "ALTER TABLE `:table`"
                .replace(":table", this.getTable());

        if (!this.getColumnDefinitions().isEmpty()) {
            String columnString = " ";

            for (ColumnDefinition columnDefinition : this.getColumnDefinitions()) {
                // Don't add trailing comma to last column
                if (this.getColumnDefinitions().indexOf(columnDefinition) == this.getColumnDefinitions().size() - 1) {
                    columnString = columnString.concat(columnDefinition.asSql());
                } else {
                    columnString = columnString.concat(columnDefinition.asSql().concat(", "));
                }
            }

            tableString = tableString.concat(columnString);
        }

        return tableString;
    }

    private String getDropSql() {
        return "DROP TABLE IF EXISTS `:table`".replace(":table", this.getTable());
    }
}
