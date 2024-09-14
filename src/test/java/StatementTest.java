
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.github.ryanlaverick.framework.database.Argument;
import com.github.ryanlaverick.framework.database.ArgumentType;
import com.github.ryanlaverick.framework.database.Statement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class StatementTest {
    private final Statement statement = new Statement();

    @ParameterizedTest
    @MethodSource("provideValuesForTranslation")
    void translate(ArgumentType type, Object value) throws SQLException {
        Argument argument = new Argument(type, value);
        List<Argument> argumentList = new ArrayList<>();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        argumentList.add(argument);

        this.statement.translateArgumentType(argumentList, preparedStatement, argument);

        switch (type) {
            case BOOLEAN:
                verify(preparedStatement).setBoolean(1, (Boolean) value);
                break;
            case BYTE:
                verify(preparedStatement).setByte(1, (Byte) value);
                break;
            case INT:
                verify(preparedStatement).setInt(1, (int) value);
                break;
            case LONG:
                verify(preparedStatement).setLong(1, (long) value);
                break;
            case FLOAT:
                verify(preparedStatement).setFloat(1, (float) value);
                break;
            case DOUBLE:
                verify(preparedStatement).setDouble(1, (double) value);
                break;
            case STRING:
                verify(preparedStatement).setString(1, (String) value);
                break;
            case DATE:
                verify(preparedStatement).setDate(1, (Date) value);
                break;
            case TIME:
                verify(preparedStatement).setTime(1, (Time) value);
                break;
            case TIMESTAMP:
                verify(preparedStatement).setTimestamp(1, (Timestamp) value);
                break;
        }
    }

    private static Stream<Arguments> provideValuesForTranslation() {
        return Stream.of(
                Arguments.of(ArgumentType.BOOLEAN, true),
                Arguments.of(ArgumentType.BYTE, (byte) 1),
                Arguments.of(ArgumentType.INT, 1),
                Arguments.of(ArgumentType.LONG, (long) 1726266348),
                Arguments.of(ArgumentType.FLOAT, (float) 1.1),
                Arguments.of(ArgumentType.DOUBLE, (double) 1.99),
                Arguments.of(ArgumentType.STRING, "one"),
                Arguments.of(ArgumentType.DATE, new Date(1726266348)),
                Arguments.of(ArgumentType.TIME, new Time(1726266348)),
                Arguments.of(ArgumentType.TIMESTAMP, new Timestamp(1726266348))
        );
    }
}
