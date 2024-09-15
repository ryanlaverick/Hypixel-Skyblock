package com.github.ryanlaverick.framework.database;

public class Argument {
    private final ArgumentType type;
    private final Object value;

    public Argument(ArgumentType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public ArgumentType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
