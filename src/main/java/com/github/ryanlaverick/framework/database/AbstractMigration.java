package com.github.ryanlaverick.framework.database;

import com.github.ryanlaverick.framework.database.exceptions.MigrationFailedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public abstract class AbstractMigration {
    protected abstract Schema getSchema();

    public boolean run() {
        try {
            Statement statement = new Statement();
            boolean result = statement.execute(this.getSchema().asSql(), Collections.emptyList());

            if (!result) {
                throw new MigrationFailedException(this.getClass().getName());
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new MigrationFailedException(this.getClass().getName());
        }

        return true;
    }
}
