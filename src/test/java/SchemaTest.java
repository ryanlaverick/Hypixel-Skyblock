import com.github.ryanlaverick.framework.database.ColumnDefinition;
import com.github.ryanlaverick.framework.database.ColumnType;
import com.github.ryanlaverick.framework.database.Schema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchemaTest {
    void createSchema() {

    }

    @Test
    void dropSchema() {
        Schema schema = new Schema("players")
                .drop();

        assertEquals("DROP TABLE IF EXISTS players", schema.asSql());
    }

    void alterSchema() {
        Schema schema = new Schema("players")
                .alter()
                .addColumnDefinition(new ColumnDefinition("DROP COLUMN player_name"))
                .addColumnDefinition(new ColumnDefinition("DROP COLUMN player_uuid"))
                .addColumnDefinition(new ColumnDefinition("uuid", ColumnType.VARCHAR).primaryKey());
    }
}
