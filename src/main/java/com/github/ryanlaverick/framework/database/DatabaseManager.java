package com.github.ryanlaverick.framework.database;

import com.github.ryanlaverick.Skyblock;
import com.github.ryanlaverick.framework.database.exceptions.ConnectionAlreadyOpenException;
import com.github.ryanlaverick.framework.database.exceptions.InvalidConnectionCredentialsException;

import java.sql.SQLException;

public class DatabaseManager {
    private final Skyblock skyblock;
    private final Connection connection;

    public DatabaseManager(Skyblock skyblock) {
        this.skyblock = skyblock;
        this.connection = new HikariConnection("localhost", "3306", "skyblock", "root", "", 10);
    }

    public void establishConnection() {
        try {
            this.connection.openConnection();
        } catch (ConnectionAlreadyOpenException e) {
            this.skyblock.getLogger().severe("[DATABASE] Unable to open a new connection to the Database, as one already exists.");
        } catch (InvalidConnectionCredentialsException e) {
            this.skyblock.getLogger().severe("[DATABASE] Unable to establish a connection to the Database using the provided credentials. Are they correct?");
        } catch (SQLException e) {
            this.skyblock.getLogger().severe("[DATABASE] Unable to establish a connection to the Database.");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
