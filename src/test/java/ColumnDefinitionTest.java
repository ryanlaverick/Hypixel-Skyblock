import com.github.ryanlaverick.framework.database.ColumnDefinition;
import com.github.ryanlaverick.framework.database.ColumnType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColumnDefinitionTest {

    @ParameterizedTest
    @EnumSource(ColumnType.class)
    void baseDefinition(ColumnType type) {
        ColumnDefinition columnDefinition = new ColumnDefinition("column", type);

        if (type == ColumnType.VARCHAR) {
            assertEquals("column :type(255)".replace(":type", type.getSql()), columnDefinition.asSql());
        } else {
            assertEquals("column ".concat(type.getSql()), columnDefinition.asSql());
        }
    }

    @Test
    void sizeDefinition() {
        ColumnDefinition columnDefinition = new ColumnDefinition("column", ColumnType.VARCHAR);
        columnDefinition = columnDefinition.size(10);

        assertEquals("column VARCHAR(10)", columnDefinition.asSql());
    }

    @Test
    void primaryKeyDefinition() {
        ColumnDefinition columnDefinition = new ColumnDefinition("column", ColumnType.VARCHAR);
        columnDefinition = columnDefinition.primaryKey();

        assertEquals("column VARCHAR(255) PRIMARY KEY", columnDefinition.asSql());
    }

    @Test
    void rawDefinition() {
        ColumnDefinition columnDefinition = new ColumnDefinition("column", "VARCHAR(255) DEFAULT 'Player'");

        assertEquals("column VARCHAR(255) DEFAULT 'Player'", columnDefinition.asSql());
    }
}
