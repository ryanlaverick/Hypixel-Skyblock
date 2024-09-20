package com.github.ryanlaverick;

import com.github.ryanlaverick.framework.cache.Cache;
import com.github.ryanlaverick.framework.database.DatabaseManager;
import com.github.ryanlaverick.framework.event.BrokerManager;
import com.github.ryanlaverick.framework.event.exceptions.BrokerNotInitializedException;
import com.github.ryanlaverick.framework.filesystem.CustomFile;
import com.github.ryanlaverick.framework.filesystem.FileSystemManager;
import com.github.ryanlaverick.framework.filesystem.exceptions.FileSystemNotInitializedException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class Skyblock extends JavaPlugin {
    private Cache cache;

    private FileSystemManager fileSystemManager;
    private BrokerManager brokerManager;

    @Override
    public void onEnable() {
        this.cache = new Cache();

        this.fileSystemManager = new FileSystemManager(this);
        this.fileSystemManager.initFiles();

        for (CustomFile file : this.getFileSystemManager().getFiles()) {
            FileConfiguration fileConfiguration = file.getFileConfiguration();
            Set<String> keys = fileConfiguration.getKeys(false);

            for (String key : keys) {
                this.cache.add(key, fileConfiguration.get(key));
            }
        }

        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.establishConnection();

        this.brokerManager = new BrokerManager(this);
        this.brokerManager.establishConnection();
    }

    @Override
    public void onDisable() {
        this.brokerManager.closeConnection();
        this.cache.clear();
    }

    public Cache getCache() {
        return cache;
    }

    public FileSystemManager getFileSystemManager() {
        if (this.fileSystemManager == null) {
            throw new FileSystemNotInitializedException();
        }

        return this.fileSystemManager;
    }

    public BrokerManager getBrokerManager() {
        if (this.brokerManager == null) {
            throw new BrokerNotInitializedException();
        }

        return brokerManager;
    }
}
