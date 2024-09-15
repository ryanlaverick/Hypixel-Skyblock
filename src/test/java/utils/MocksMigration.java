package utils;

import com.github.ryanlaverick.framework.database.AbstractMigration;
import com.github.ryanlaverick.framework.database.Schema;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class MocksMigration {
    public Schema getSchema(AbstractMigration migration) {
        try {
            Method method = migration.getClass().getDeclaredMethod("getSchema");
            method.setAccessible(true);

            return (Schema) method.invoke(migration);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            fail("Method `getSchema()` could not be marked as accessible.");
        }

        return null;
    }
}
