package com.github.ryanlaverick;

import com.github.ryanlaverick.framework.cache.Cache;
import com.github.ryanlaverick.framework.cache.CacheManager;
import com.github.ryanlaverick.framework.cache.exceptions.CacheNotInitializedException;
import com.github.ryanlaverick.framework.database.DatabaseManager;
import com.github.ryanlaverick.framework.event.BrokerManager;
import com.github.ryanlaverick.framework.event.exceptions.BrokerNotInitializedException;
import com.github.ryanlaverick.framework.filesystem.FileSystemManager;
import com.github.ryanlaverick.framework.filesystem.exceptions.FileSystemNotInitializedException;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class Skyblock extends JavaPlugin {
    private CacheManager cacheManager;
    private FileSystemManager fileSystemManager;
    private BrokerManager brokerManager;

    @Override
    public void onEnable() {
        this.fileSystemManager = new FileSystemManager(this);
        this.fileSystemManager.initFiles();

        // Ensure Config cache is set up before Database and Broker managers as they utilise cached values
        this.cacheManager = new CacheManager(this);
        this.cacheManager.setupConfigCache();

        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.establishConnection();

        this.brokerManager = new BrokerManager(this);
        this.brokerManager.establishConnection();
    }

    @Override
    public void onDisable() {
        this.brokerManager.closeConnection();

        for (Map.Entry<String, Cache> entrySet : this.getCacheManager().getCaches().entrySet()) {
            Cache cache = entrySet.getValue();

            cache.clear();
        }
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

    public CacheManager getCacheManager() {
        if (this.cacheManager == null) {
            throw new CacheNotInitializedException();
        }

        return cacheManager;
    }
}
