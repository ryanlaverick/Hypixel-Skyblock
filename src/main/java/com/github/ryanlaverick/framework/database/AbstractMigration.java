package com.github.ryanlaverick.framework.database;

import com.github.ryanlaverick.framework.database.exceptions.MigrationFailedException;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

public abstract class AbstractMigration {
    protected abstract Schema getSchema();

    public void run() {
        try {
            Statement statement = new Statement();
            statement.execute(this.getSchema().asSql(), Collections.emptyList());
        } catch (ExecutionException | InterruptedException e) {
            throw new MigrationFailedException(this.getClass().getName());
        }
    }
}
