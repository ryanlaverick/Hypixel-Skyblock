package com.github.ryanlaverick.skyblock.modules.player.migrations;

import com.github.ryanlaverick.Skyblock;
import com.github.ryanlaverick.framework.database.AbstractMigration;
import com.github.ryanlaverick.framework.database.AbstractMigrationManager;

import java.util.Arrays;
import java.util.List;

public final class PlayerMigrationManager extends AbstractMigrationManager {
    public PlayerMigrationManager(Skyblock skyblock) {
        super(skyblock);
    }

    @Override
    protected List<Class<? extends AbstractMigration>> getMigrations() {
        return Arrays.asList(
                CreatePlayersTableMigration.class
        );
    }
}
