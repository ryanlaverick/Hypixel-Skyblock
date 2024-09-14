package framework.database;

import com.github.ryanlaverick.framework.database.DropColumnDefinition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DropColumnDefinitionTest {
    @Test
    void dropDefinition() {
        DropColumnDefinition columnDefinition = new DropColumnDefinition("uuid");

        assertEquals("uuid", columnDefinition.getColumn());
        assertEquals("DROP COLUMN uuid", columnDefinition.asSql());
    }
}
