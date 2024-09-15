package com.github.ryanlaverick.framework.database;

import com.github.ryanlaverick.Skyblock;
import com.github.ryanlaverick.framework.database.exceptions.MigrationFailedException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class AbstractMigrationManager {
    private final Skyblock skyblock;

    protected AbstractMigrationManager(Skyblock skyblock) {
        this.skyblock = skyblock;
    }

    protected abstract List<Class<? extends AbstractMigration>> getMigrations();

    public void migrate() {
        for (Class<? extends AbstractMigration> clazz : getMigrations()) {
            try {
                this.skyblock.getLogger().info("Running migration: :migration".replace(":migration", clazz.getSimpleName()));

                clazz.getDeclaredConstructor().newInstance().run();

                this.skyblock.getLogger().info("Successfully ran migration: :migration".replace(":migration", clazz.getSimpleName()));
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new MigrationFailedException(clazz.getName());
            }
        }
    }
}
