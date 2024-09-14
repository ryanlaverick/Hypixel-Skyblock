package framework.database;

import com.github.ryanlaverick.framework.database.AlterColumnDefinition;
import com.github.ryanlaverick.framework.database.ColumnType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlterColumnDefinitionTest {

    @ParameterizedTest
    @EnumSource(ColumnType.class)
    void baseDefinition(ColumnType type) {
        AlterColumnDefinition columnDefinition = new AlterColumnDefinition("column", type);

        assertEquals("column", columnDefinition.getColumn());

        if (type == ColumnType.VARCHAR) {
            assertEquals("ADD COLUMN column :type(255) NOT NULL".replace(":type", type.getSql()), columnDefinition.asSql());
        } else {
            assertEquals("ADD COLUMN column :type NOT NULL".replace(":type", type.getSql()), columnDefinition.asSql());
        }
    }

    @Test
    void alterDefinition() {
        AlterColumnDefinition columnDefinition = new AlterColumnDefinition("column", ColumnType.VARCHAR)
                .alter();

        assertTrue(columnDefinition.isAltering());

        assertEquals("ALTER COLUMN column VARCHAR(255) NOT NULL", columnDefinition.asSql());
    }

    @Test
    void sizeDefinition() {
        AlterColumnDefinition columnDefinition = new AlterColumnDefinition("column", ColumnType.VARCHAR)
                .size(10);

        assertEquals(10, columnDefinition.getSize());
        assertTrue(columnDefinition.hasSize());
        assertEquals("ADD COLUMN column VARCHAR(10) NOT NULL", columnDefinition.asSql());
    }

    @Test
    void primaryKeyDefinition() {
        AlterColumnDefinition columnDefinition = new AlterColumnDefinition("column", ColumnType.VARCHAR)
                .primaryKey();

        assertTrue(columnDefinition.isPrimary());
        assertEquals("ADD COLUMN column VARCHAR(255) NOT NULL PRIMARY KEY", columnDefinition.asSql());
    }

    @Test
    void nullableDefinition() {
        AlterColumnDefinition columnDefinition = new AlterColumnDefinition("column", ColumnType.VARCHAR)
                .nullable();

        assertTrue(columnDefinition.isNullable());
        assertEquals("ADD COLUMN column VARCHAR(255)", columnDefinition.asSql());
    }
}
