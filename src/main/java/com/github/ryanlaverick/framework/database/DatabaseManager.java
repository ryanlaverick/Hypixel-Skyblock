package com.github.ryanlaverick.framework.database;

import com.github.ryanlaverick.Skyblock;
import com.github.ryanlaverick.framework.database.exceptions.ConnectionAlreadyOpenException;
import com.github.ryanlaverick.framework.database.exceptions.InvalidConnectionCredentialsException;

import java.sql.SQLException;

public class DatabaseManager {
    private final Skyblock skyblock;
    private static Connection connection = null;

    public DatabaseManager(Skyblock skyblock) {
        this.skyblock = skyblock;
        
        connection = new HikariConnection(
                this.skyblock.getCache().getString("DATABASE_HOST"),
                this.skyblock.getCache().getString("DATABASE_PORT"),
                this.skyblock.getCache().getString("DATABASE_NAME"),
                this.skyblock.getCache().getString("DATABASE_USER"),
                this.skyblock.getCache().getString("DATABASE_PASSWORD"),
                this.skyblock.getCache().getInt("DATABASE_MAX_POOL_SIZE")
        );
    }

    public void establishConnection() {
        try {
            connection.openConnection();
        } catch (ConnectionAlreadyOpenException e) {
            this.skyblock.getLogger().severe("[DATABASE] Unable to open a new connection to the Database, as one already exists.");
        } catch (InvalidConnectionCredentialsException e) {
            this.skyblock.getLogger().severe("[DATABASE] Unable to establish a connection to the Database using the provided credentials. Are they correct?");
        } catch (SQLException e) {
            this.skyblock.getLogger().severe("[DATABASE] Unable to establish a connection to the Database.");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
