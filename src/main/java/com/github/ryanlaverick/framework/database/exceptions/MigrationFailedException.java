package com.github.ryanlaverick.framework.database.exceptions;

public class MigrationFailedException extends RuntimeException {
    public MigrationFailedException(String migration) {
        super("Migration :migration failed!".replace(":migration", migration));
    }
}
