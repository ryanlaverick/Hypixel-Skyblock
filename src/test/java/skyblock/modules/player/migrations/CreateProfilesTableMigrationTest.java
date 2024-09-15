package skyblock.modules.player.migrations;

import com.github.ryanlaverick.framework.database.Schema;
import com.github.ryanlaverick.skyblock.modules.player.migrations.CreateProfilesTableMigration;
import org.junit.jupiter.api.Test;
import utils.MocksMigration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateProfilesTableMigrationTest extends MocksMigration {

    @Test
    void testSchema() {
        Schema result = this.getSchema(new CreateProfilesTableMigration());

        assertEquals("CREATE TABLE IF NOT EXISTS `profiles` (`id` VARCHAR(36) NOT NULL PRIMARY KEY, `player_uuid` VARCHAR(36) NOT NULL, FOREIGN KEY (`player_uuid`) REFERENCES `players`(`uuid`), `name` VARCHAR(255) NOT NULL, `created_at` BIGINT(20) NOT NULL, `updated_at` BIGINT(20), `deleted_at` BIGINT(20))", result.asSql());
    }
}
