package framework.database;

import com.github.ryanlaverick.framework.database.ColumnType;
import com.github.ryanlaverick.framework.database.CreateColumnDefinition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateColumnDefinitionTest {

    @ParameterizedTest
    @EnumSource(ColumnType.class)
    void baseDefinition(ColumnType type) {
        CreateColumnDefinition columnDefinition = new CreateColumnDefinition("column", type);

        assertEquals("column", columnDefinition.getColumn());

        if (type == ColumnType.VARCHAR) {
            assertEquals("`column` :type(255) NOT NULL".replace(":type", type.getSql()), columnDefinition.asSql());
        } else {
            assertEquals("`column` :type NOT NULL".replace(":type", type.getSql()), columnDefinition.asSql());
        }
    }

    @Test
    void lengthDefinition() {
        CreateColumnDefinition columnDefinition = new CreateColumnDefinition("column", ColumnType.VARCHAR)
                .length(10);

        assertEquals(10, columnDefinition.getLength());
        assertTrue(columnDefinition.hasLength());
        assertEquals("`column` VARCHAR(10) NOT NULL", columnDefinition.asSql());
    }

    @Test
    void primaryKeyDefinition() {
        CreateColumnDefinition columnDefinition = new CreateColumnDefinition("column", ColumnType.VARCHAR)
                .primaryKey();

        assertTrue(columnDefinition.isPrimary());
        assertEquals("`column` VARCHAR(255) NOT NULL PRIMARY KEY", columnDefinition.asSql());
    }

    @Test
    void nullableDefinition() {
        CreateColumnDefinition columnDefinition = new CreateColumnDefinition("column", ColumnType.VARCHAR)
                .nullable();

        assertTrue(columnDefinition.isNullable());
        assertEquals("`column` VARCHAR(255)", columnDefinition.asSql());
    }
}
