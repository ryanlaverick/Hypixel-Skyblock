package com.github.ryanlaverick.framework.database;

import java.sql.*;
import java.sql.Connection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Statement {
    public ResultSet query(String sql, List<Argument> arguments) throws ExecutionException, InterruptedException {
        CompletableFuture<ResultSet> result = CompletableFuture.supplyAsync(() -> {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
               connection = DatabaseManager.getConnection().getConnection();
               preparedStatement = connection.prepareStatement(sql);

               for (Argument argument : arguments) {
                   this.translateArgumentType(arguments, preparedStatement, argument);
               }

               resultSet = preparedStatement.executeQuery();

            }

            catch (SQLException e) {
                e.printStackTrace();
            }

            finally {
                try {
                    connection.close();
                    preparedStatement.close();
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            return resultSet;
        });

        return result.get();
    }

    public void translateArgumentType(List<Argument> arguments, PreparedStatement preparedStatement, Argument argument) throws SQLException {
        int position = arguments.indexOf(argument) + 1; // Indexes always start at 0

        switch (argument.getType()) {
            case BOOLEAN:
                preparedStatement.setBoolean(position, (boolean) argument.getValue());
                break;
            case BYTE:
                preparedStatement.setByte(position, (byte) argument.getValue());
                break;
            case INT:
                preparedStatement.setInt(position, (int) argument.getValue());
                break;
            case LONG:
                preparedStatement.setLong(position, (long) argument.getValue());
                break;
            case FLOAT:
                preparedStatement.setFloat(position, (float) argument.getValue());
                break;
            case DOUBLE:
                preparedStatement.setDouble(position, (double) argument.getValue());
                break;
            case STRING:
                preparedStatement.setString(position, (String) argument.getValue());
                break;
            case DATE:
                preparedStatement.setDate(position, (Date) argument.getValue());
                break;
            case TIME:
                preparedStatement.setTime(position, (Time) argument.getValue());
                break;
            case TIMESTAMP:
                preparedStatement.setTimestamp(position, (Timestamp) argument.getValue());
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
