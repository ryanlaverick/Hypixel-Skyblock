package skyblock.modules.player.migrations;

import com.github.ryanlaverick.framework.database.Schema;
import com.github.ryanlaverick.skyblock.modules.player.migrations.CreatePlayersTableMigration;
import org.junit.jupiter.api.Test;
import utils.MocksMigration;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreatePlayersTableMigrationTest extends MocksMigration {

    @Test
    void testSchema() {
        Schema result = this.getSchema(new CreatePlayersTableMigration());

        assertEquals("CREATE TABLE IF NOT EXISTS `players` (`uuid` VARCHAR(36) NOT NULL PRIMARY KEY, `first_joined_at` BIGINT(20) NOT NULL, `last_seen_at` BIGINT(20) NOT NULL)", result.asSql());
    }
}
