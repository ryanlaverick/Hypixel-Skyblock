package com.github.ryanlaverick;

import com.github.ryanlaverick.framework.database.DatabaseManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Skyblock extends JavaPlugin {
    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        this.databaseManager = new DatabaseManager(this);
        this.databaseManager.establishConnection();
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
