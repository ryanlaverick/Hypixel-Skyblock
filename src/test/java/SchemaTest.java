import com.github.ryanlaverick.framework.database.Schema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchemaTest {
    private final Schema schema = new Schema();

    @Test
    void setName() {
        schema.table("players");

        assertEquals("players", schema.getTable());
    }

    @Test
    void setNameWithReassignment() {
        Schema newSchema = schema.table("players");

        assertEquals("players", newSchema.getTable());
    }
}
