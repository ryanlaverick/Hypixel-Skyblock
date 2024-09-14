package framework.database;

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

    }
}
