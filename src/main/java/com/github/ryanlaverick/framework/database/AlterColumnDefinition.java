package com.github.ryanlaverick.framework.database;

public class AlterColumnDefinition implements ColumnDefinition {
    private final String column;
    private final ColumnType type;

    private boolean hasSize = false;
    private int size = 0;

    private boolean isPrimary = false;
    private boolean isNullable = false;

    private boolean isAdding = true;
    private boolean isAltering = false;

    public AlterColumnDefinition(String column, ColumnType type) {
        this.column = column;
        this.type = type;
    }

    public AlterColumnDefinition size(int size) {
        this.hasSize = true;
        this.size = size;

        return this;
    }

    public AlterColumnDefinition primaryKey() {
        this.isPrimary = true;

        return this;
    }

    public AlterColumnDefinition nullable() {
        this.isNullable = true;

        return this;
    }

    public AlterColumnDefinition add() {
        this.isAdding = true;
        this.isAltering = false;

        return this;
    }

    public AlterColumnDefinition alter() {
        this.isAdding = false;
        this.isAltering = true;

        return this;
    }

    public String getColumn() {
        return column;
    }

    public ColumnType getType() {
        return type;
    }

    public boolean hasSize() {
        return hasSize;
    }

    public int getSize() {
        return size;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public boolean isAltering() {
        return isAltering;
    }

    public boolean isAdding() {
        return isAdding;
    }

    public String asSql() {
        String baseString = "";

        if (this.isAltering()) {
            baseString = "ALTER COLUMN :column :type";
        } else {
            baseString = "ADD COLUMN :column :type";
        }

        if (this.hasSize()) {
            baseString = baseString.concat("(:size)");
        } else {
            if (this.getType() == ColumnType.VARCHAR) {
                baseString = baseString.concat("(255)");
            }
        }

        if (!this.isNullable()) {
            baseString = baseString.concat(" NOT NULL");
        }

        if (this.isPrimary()) {
            baseString = baseString.concat(" PRIMARY KEY");
        }

        baseString = baseString
                .replace(":column", this.getColumn())
                .replace(":type", this.getType().getSql())
                .replace(":size", String.valueOf(this.getSize()));

        return baseString;
    }
}
