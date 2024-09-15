package framework.database;

import com.github.ryanlaverick.framework.database.RawColumnDefinition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RawColumnDefinitionTest {

    @Test
    void rawDefinition() {
        RawColumnDefinition columnDefinition = new RawColumnDefinition("FOREIGN KEY (`uuid`) REFERENCES players(player_uuid)");

        assertEquals("", columnDefinition.getColumn());
        assertEquals("FOREIGN KEY (`uuid`) REFERENCES players(player_uuid)", columnDefinition.asSql());
    }
}
