package com.github.ryanlaverick.framework.database;

public final class CreateColumnDefinition implements ColumnDefinition {
    private final String column;
    private final ColumnType type;

    private boolean hasLength = false;
    private int length = 0;

    private boolean isPrimary = false;
    private boolean isNullable = false;

    public CreateColumnDefinition(String column, ColumnType type) {
        this.column = column;
        this.type = type;
    }

    public CreateColumnDefinition length(int length) {
        this.hasLength = true;
        this.length = length;

        return this;
    }

    public CreateColumnDefinition primaryKey() {
        this.isPrimary = true;

        return this;
    }

    public CreateColumnDefinition nullable() {
        this.isNullable = true;

        return this;
    }

    public String getColumn() {
        return column;
    }

    public ColumnType getType() {
        return type;
    }

    public boolean hasLength() {
        return hasLength;
    }

    public int getLength() {
        return length;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public String asSql() {
        String baseString = "`:column` :type";

        if (this.hasLength()) {
            baseString = baseString.concat("(:length)");
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
                .replace(":length", String.valueOf(this.getLength()));

        return baseString;
    }
}
