package framework.cache;

import com.github.ryanlaverick.framework.cache.Cache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CacheTest {
    private final Cache cache = new Cache();

    @ParameterizedTest
    @MethodSource("provideValues")
    void cacheItem(Object value) {
        this.cache.add("FOO", value);

        assertEquals(value, this.cache.get("FOO"));
    }

    private static Stream<Arguments> provideValues() {
        return Stream.of(
                Arguments.of(true),
                Arguments.of((byte) 1),
                Arguments.of(1),
                Arguments.of((long) 1726266348),
                Arguments.of((float) 1.1),
                Arguments.of(1.99),
                Arguments.of("one"),
                Arguments.of('c'),
                Arguments.of( new Date(1726266348)),
                Arguments.of(new Time(1726266348)),
                Arguments.of(new Timestamp(1726266348))
        );
    }

    @Test
    void removeItem() {
        this.cache.add("FOO", "BAR");
        assertEquals(1, this.cache.getCache().size());

        this.cache.remove("FOO");
        assertEquals(0, this.cache.getCache().size());
    }

    @Test
    void hasItem() {
        assertFalse(this.cache.has("FOO"));

        this.cache.add("FOO", "BAR");

        assertTrue(this.cache.has("FOO"));
    }

    @Test
    void getItem() {
        this.cache.add("FOO", "BAR");

        assertEquals("BAR", this.cache.get("FOO"));
    }

    @Test
    void getString() {
        this.cache.add("FOO", "BAR");

        assertEquals("BAR", this.cache.getString("FOO"));
    }

    @Test
    void getInt() {
        this.cache.add("FOO", 1);

        assertEquals(1, this.cache.getInt("FOO"));
    }
}
