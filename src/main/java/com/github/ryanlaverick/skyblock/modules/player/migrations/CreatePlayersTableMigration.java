package com.github.ryanlaverick.skyblock.modules.player.migrations;

import com.github.ryanlaverick.framework.database.*;

public final class CreatePlayersTableMigration extends AbstractMigration {
    @Override
    protected Schema getSchema() {
        return new Schema("players")
                .create()
                .primaryUuid("uuid")
                .timestamp("first_joined_at")
                .timestamp("last_seen_at");
    }
}
