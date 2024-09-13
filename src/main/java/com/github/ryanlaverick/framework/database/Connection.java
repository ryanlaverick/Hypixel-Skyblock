package com.github.ryanlaverick.framework.database;

import com.github.ryanlaverick.framework.database.exceptions.ConnectionAlreadyOpenException;
import com.github.ryanlaverick.framework.database.exceptions.ConnectionNotOpenException;
import com.github.ryanlaverick.framework.database.exceptions.InvalidConnectionCredentialsException;
import com.github.ryanlaverick.framework.database.exceptions.UnclosableConnectionException;

import java.sql.SQLException;

public interface Connection {
    void openConnection() throws ConnectionAlreadyOpenException, InvalidConnectionCredentialsException, SQLException;

    void closeConnection() throws ConnectionNotOpenException, UnclosableConnectionException, SQLException;

    boolean connectionOpen() throws SQLException;

    java.sql.Connection getConnection() throws SQLException;
}
