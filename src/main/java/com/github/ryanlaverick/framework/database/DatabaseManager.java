package com.github.ryanlaverick.framework.database;

import com.github.ryanlaverick.Skyblock;
import com.github.ryanlaverick.framework.database.exceptions.ConnectionAlreadyOpenException;
import com.github.ryanlaverick.framework.database.exceptions.InvalidConnectionCredentialsException;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.SQLException;

public class DatabaseManager {
    private final Skyblock skyblock;
    private static Connection connection = null;

    public DatabaseManager(Skyblock skyblock) {
        this.skyblock = skyblock;

        FileConfiguration databaseFile = this.skyblock.getFileSystemManager().getDatabaseFile().getFileConfiguration();
        
        connection = new HikariConnection(
                databaseFile.getString("DATABASE_HOST"),
                databaseFile.getString("DATABASE_PORT"),
                databaseFile.getString("DATABASE_NAME"),
                databaseFile.getString("DATABASE_USER"),
                databaseFile.getString("DATABASE_PASSWORD"),
                databaseFile.getInt("DATABASE_MAX_POOL_SIZE")
        );
    }

    public void establishConnection() {
        try {
            connection.openConnection();

            PlayerMigrationManager playerMigrationManager = new PlayerMigrationManager(this.skyblock);
            playerMigrationManager.migrate();
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
