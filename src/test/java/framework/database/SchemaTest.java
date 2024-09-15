package framework.database;

import com.github.ryanlaverick.framework.database.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchemaTest {

    @Test
    void createSchema() {
        Schema schema = new Schema("players")
                .create()
                .addColumnDefinition(new CreateColumnDefinition("uuid", ColumnType.VARCHAR).length(36).primaryKey())
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
                .addColumnDefinition(new AlterColumnDefinition("player_uuid", ColumnType.VARCHAR).add().length(36).nullable());

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

    @Test
    void primaryUuid() {
        Schema schema = new Schema("players")
                .create()
                .primaryUuid("id");

        assertEquals("CREATE TABLE IF NOT EXISTS `players` (`id` VARCHAR(36) NOT NULL PRIMARY KEY)", schema.asSql());
    }

    @Test
    void foreignUuid() {
        Schema schema = new Schema("profiles")
                .foreignUuid("player_uuid", "players", "uuid");

        assertEquals("CREATE TABLE IF NOT EXISTS `profiles` (`player_uuid` VARCHAR(36) NOT NULL, FOREIGN KEY (`player_uuid`) REFERENCES `players`(`uuid`))", schema.asSql());
    }

    @Test
    void timestamp() {
        Schema schema = new Schema("players")
                .timestamp("created_at");

        assertEquals("CREATE TABLE IF NOT EXISTS `players` (`created_at` BIGINT(20) NOT NULL)", schema.asSql());
    }

    @Test
    void nullableTimestamp() {
        Schema schema = new Schema("players")
                .nullableTimestamp("created_at");

        assertEquals("CREATE TABLE IF NOT EXISTS `players` (`created_at` BIGINT(20))", schema.asSql());
    }

    @Test
    void string() {
        Schema schema = new Schema("players")
                .string("name");

        assertEquals("CREATE TABLE IF NOT EXISTS `players` (`name` VARCHAR(255) NOT NULL)", schema.asSql());
    }

    @Test
    void stringWithLength() {
        Schema schema = new Schema("players")
                .string("name", 32);

        assertEquals("CREATE TABLE IF NOT EXISTS `players` (`name` VARCHAR(32) NOT NULL)", schema.asSql());
    }

    @Test
    void nullableString() {
        Schema schema = new Schema("players")
                .nullableString("name");

        assertEquals("CREATE TABLE IF NOT EXISTS `players` (`name` VARCHAR(255))", schema.asSql());
    }

    @Test
    void nullableStringWithLength() {
        Schema schema = new Schema("players")
                .nullableString("name", 32);

        assertEquals("CREATE TABLE IF NOT EXISTS `players` (`name` VARCHAR(32))", schema.asSql());
    }

    @Test
    void raw() {
        Schema schema = new Schema("players")
                .raw("`rank` VARCHAR(255) DEFAULT `player`");

        assertEquals("CREATE TABLE IF NOT EXISTS `players` (`rank` VARCHAR(255) DEFAULT `player`)", schema.asSql());
    }
}
