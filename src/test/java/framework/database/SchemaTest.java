package framework.database;

import com.github.ryanlaverick.framework.database.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchemaTest {

    @Test
    void createSchema() {
        Schema schema = new Schema("players")
                .create()
                .addColumnDefinition(new CreateColumnDefinition("uuid", ColumnType.VARCHAR).size(36).primaryKey())
                .addColumnDefinition(new CreateColumnDefinition("player_name", ColumnType.VARCHAR))
                .addColumnDefinition(new CreateColumnDefinition("last_seen", ColumnType.DATETIME).nullable());

        assertEquals("CREATE TABLE IF NOT EXISTS `players` (`uuid` VARCHAR(36) NOT NULL PRIMARY KEY, `player_name` VARCHAR(255) NOT NULL, `last_seen` DATETIME)", schema.asSql());
    }

    @Test
    void alterSchemaDropsColumn() {
        Schema schema = new Schema("players")
                .alter()
                .addColumnDefinition(new DropColumnDefinition("uuid"));

        assertEquals("ALTER TABLE `players` DROP COLUMN `uuid`", schema.asSql());
    }

    @Test
    void alterSchemaAddsColumn() {
        Schema schema = new Schema("players")
                .alter()
                .addColumnDefinition(new AlterColumnDefinition("player_uuid", ColumnType.VARCHAR).add().size(36).nullable());

        assertEquals("ALTER TABLE `players` ADD COLUMN `player_uuid` VARCHAR(36)", schema.asSql());
    }

    @Test
    void dropSchema() {
        Schema schema = new Schema("players")
                .drop();

        assertEquals("DROP TABLE IF EXISTS `players`", schema.asSql());
    }

    @Test
    void rawSchema() {
        Schema schema = new Schema("players")
                .alter()
                .addColumnDefinition(new RawColumnDefinition("MODIFY COLUMN `rank` VARCHAR(255) DEFAULT 'player'"));

        assertEquals("ALTER TABLE `players` MODIFY COLUMN `rank` VARCHAR(255) DEFAULT 'player'", schema.asSql());
    }
}
