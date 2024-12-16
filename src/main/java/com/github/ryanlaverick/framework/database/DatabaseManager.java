package com.github.ryanlaverick.framework.database;

import com.github.ryanlaverick.Skyblock;
import com.github.ryanlaverick.framework.cache.Cache;
import com.github.ryanlaverick.framework.cache.CacheIdentifier;
import com.github.ryanlaverick.framework.database.exceptions.ConnectionAlreadyOpenException;
import com.github.ryanlaverick.framework.database.exceptions.InvalidConnectionCredentialsException;

import java.sql.SQLException;

public class DatabaseManager {
    private final Skyblock skyblock;
    private static Connection connection = null;

    public DatabaseManager(Skyblock skyblock) {
        this.skyblock = skyblock;

        Cache config = this.skyblock.getCacheManager().getCache(CacheIdentifier.CONFIG);
        
        connection = new HikariConnection(
                config.getString("DATABASE_HOST"),
                config.getString("DATABASE_PORT"),
                config.getString("DATABASE_NAME"),
                config.getString("DATABASE_USER"),
                config.getString("DATABASE_PASSWORD"),
                config.getInt("DATABASE_MAX_POOL_SIZE")
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
        } finally {
            this.skyblock.getLogger().info("[DATABASE] Connection established successfully!");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
