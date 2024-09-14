package com.github.ryanlaverick.framework.database;

public class ColumnDefinition {
    private final String column;
    private ColumnType type = ColumnType.VARCHAR;

    private String raw = "";
    private boolean isUsingRaw = false;

    private boolean hasSize = false;
    private int size = 0;

    private boolean isPrimary = false;

    public ColumnDefinition(String column, String raw) {
        this.column = column;
        this.isUsingRaw = true;
        this.raw = raw;
    }

    public ColumnDefinition(String column, ColumnType type) {
        this.column = column;
        this.type = type;
    }

    public ColumnDefinition size(int size) {
        this.hasSize = true;
        this.size = size;

        return this;
    }

    public ColumnDefinition primaryKey() {
        this.isPrimary = true;

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

    public String getRaw() {
        return raw;
    }

    public boolean isUsingRaw() {
        return isUsingRaw;
    }

    public String asSql() {
        if (this.isUsingRaw()) {
            return ":column :raw"
                    .replace(":column", this.getColumn())
                    .replace(":raw", this.getRaw());
        }

        String baseString = ":column :type";

        if (this.hasSize()) {
            baseString = baseString.concat("(:size)");
        } else {
            if (this.getType() == ColumnType.VARCHAR) {
                baseString = baseString.concat("(255)");
            }
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
