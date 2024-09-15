package com.github.ryanlaverick.skyblock.modules.player.migrations;

import com.github.ryanlaverick.framework.database.*;

public final class CreateProfilesTableMigration extends AbstractMigration {

    @Override
    protected Schema getSchema() {
        return new Schema("profiles")
                .create()
                .primaryUuid("id")
                .foreignUuid("player_uuid", "players", "uuid")
                .string("name")
                .timestamp("created_at")
                .nullableTimestamp("updated_at")
                .nullableTimestamp("deleted_at");
    }
}
