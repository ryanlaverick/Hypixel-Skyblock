package com.github.ryanlaverick.framework.database;

import com.github.ryanlaverick.framework.database.exceptions.ConnectionAlreadyOpenException;
import com.github.ryanlaverick.framework.database.exceptions.ConnectionNotOpenException;
import com.github.ryanlaverick.framework.database.exceptions.InvalidConnectionCredentialsException;
import com.github.ryanlaverick.framework.database.exceptions.UnclosableConnectionException;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;

public final class HikariConnection implements Connection {
    private final String host;
    private final String port;
    private final String database;
    private final String username;
    private final String password;
    private final int maximumConnections;

    private HikariDataSource hikariDataSource;

    public HikariConnection(String host, String port, String database, String username, String password, int maximumConnections) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        this.maximumConnections = maximumConnections;
    }

    @Override
    public void openConnection() throws ConnectionAlreadyOpenException, InvalidConnectionCredentialsException, SQLException {
        if (this.getConnection() != null) {
            throw new ConnectionAlreadyOpenException();
        }

        try {
            HikariDataSource connection = new HikariDataSource();
            connection.setMaximumPoolSize(this.maximumConnections);

            connection.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
            connection.addDataSourceProperty("serverName", this.host);
            connection.addDataSourceProperty("port", this.port);
            connection.addDataSourceProperty("databaseName", this.database);
            connection.addDataSourceProperty("user", this.username);
            connection.addDataSourceProperty("password", this.password);

            this.hikariDataSource = connection;
        } catch (IllegalStateException e) {
            throw new ConnectionAlreadyOpenException();
        } catch (IllegalArgumentException e) {
            throw new InvalidConnectionCredentialsException();
        }
    }

    @Override
    public void closeConnection() throws ConnectionNotOpenException, UnclosableConnectionException, SQLException {
        if (this.getConnection() == null) {
            throw new ConnectionNotOpenException();
        }

        try {
            this.hikariDataSource.close();
        } catch (Exception e) {
            throw new UnclosableConnectionException();
        }
    }

    @Override
    public boolean connectionOpen() throws SQLException {
        if (this.getConnection() == null) {
            return false;
        }

        return !this.getConnection().isClosed();
    }

    @Override
    public java.sql.Connection getConnection() throws SQLException {
        if (this.hikariDataSource == null) {
            return null;
        }

        return this.hikariDataSource.getConnection();
    }
}
