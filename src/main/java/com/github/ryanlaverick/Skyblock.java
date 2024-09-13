package com.github.ryanlaverick;

import com.github.ryanlaverick.framework.database.DatabaseManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Skyblock extends JavaPlugin {
    @Override
    public void onEnable() {
        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.establishConnection();
    }
}
