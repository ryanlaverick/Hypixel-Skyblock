package com.github.ryanlaverick;

import com.github.ryanlaverick.framework.database.DatabaseManager;
import com.github.ryanlaverick.framework.event.BrokerManager;
import com.github.ryanlaverick.framework.filesystem.FileSystemManager;
import com.github.ryanlaverick.framework.filesystem.exceptions.FileSystemNotInitializedException;
import org.bukkit.plugin.java.JavaPlugin;

public class Skyblock extends JavaPlugin {
    private FileSystemManager fileSystemManager;
    private BrokerManager brokerManager;

    @Override
    public void onEnable() {
        fileSystemManager = new FileSystemManager(this);
        fileSystemManager.initFiles();

        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.establishConnection();

        this.brokerManager = new BrokerManager(this);
        this.brokerManager.establishConnection();
    }

    @Override
    public void onDisable() {
        this.brokerManager.closeConnection();
    }

    public FileSystemManager getFileSystemManager() {
        if (fileSystemManager == null) {
            throw new FileSystemNotInitializedException();
        }

        return fileSystemManager;
    }
}
