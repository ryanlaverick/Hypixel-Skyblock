package framework.database;

import com.github.ryanlaverick.framework.database.ColumnType;
import com.github.ryanlaverick.framework.database.CreateColumnDefinition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateColumnDefinitionTest {

    @ParameterizedTest
    @EnumSource(ColumnType.class)
    void baseDefinition(ColumnType type) {
        CreateColumnDefinition columnDefinition = new CreateColumnDefinition("column", type);

        if (type == ColumnType.VARCHAR) {
            assertEquals("column :type(255) NOT NULL".replace(":type", type.getSql()), columnDefinition.asSql());
        } else {
            assertEquals("column :type NOT NULL".replace(":type", type.getSql()), columnDefinition.asSql());
        }
    }

    @Test
    void sizeDefinition() {
        CreateColumnDefinition columnDefinition = new CreateColumnDefinition("column", ColumnType.VARCHAR);
        columnDefinition.size(10);

        assertEquals("column VARCHAR(10) NOT NULL", columnDefinition.asSql());
    }

    @Test
    void primaryKeyDefinition() {
        CreateColumnDefinition columnDefinition = new CreateColumnDefinition("column", ColumnType.VARCHAR);
        columnDefinition.primaryKey();

        assertEquals("column VARCHAR(255) NOT NULL PRIMARY KEY", columnDefinition.asSql());
    }
}
