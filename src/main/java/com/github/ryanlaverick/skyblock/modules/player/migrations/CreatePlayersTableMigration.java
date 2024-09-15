package com.github.ryanlaverick.skyblock.modules.player.migrations;

import com.github.ryanlaverick.framework.database.AbstractMigration;
import com.github.ryanlaverick.framework.database.ColumnType;
import com.github.ryanlaverick.framework.database.CreateColumnDefinition;
import com.github.ryanlaverick.framework.database.Schema;

public final class CreatePlayersTableMigration extends AbstractMigration {

    @Override
    protected Schema getSchema() {
        return new Schema("players")
                .create()
                .addColumnDefinition(new CreateColumnDefinition("uuid", ColumnType.VARCHAR).size(36).primaryKey())
                .addColumnDefinition(new CreateColumnDefinition("last_seen_at", ColumnType.DATETIME))
                .addColumnDefinition(new CreateColumnDefinition("first_joined_at", ColumnType.DATETIME));
    }
}
