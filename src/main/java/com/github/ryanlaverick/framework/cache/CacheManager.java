package com.github.ryanlaverick.framework.cache;

import com.github.ryanlaverick.Skyblock;
import com.github.ryanlaverick.framework.cache.exceptions.CacheNotFoundException;
import com.github.ryanlaverick.framework.filesystem.CustomFile;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class CacheManager {
    private final Skyblock skyblock;
    private final Map<String, Cache> caches;

    public CacheManager(Skyblock skyblock) {
        this.skyblock = skyblock;
        this.caches = new HashMap<>();

        ConfigCache configCache = new ConfigCache();
        this.caches.put(configCache.getIdentifier(), configCache);
    }

    public void setupConfigCache() {
        Cache cache = this.getCache(CacheIdentifier.CONFIG);

        for (CustomFile file : this.skyblock.getFileSystemManager().getFiles()) {
            FileConfiguration configuration = file.getFileConfiguration();
            Set<String> keys = configuration.getKeys(false);

            for (String key : keys) {
                cache.cacheEntries.put(key, configuration.get(key));
            }
        }
    }

    public Map<String, Cache> getCaches() {
        return caches;
    }

    public Cache getCache(CacheIdentifier cache) throws CacheNotFoundException {
        if (!this.caches.containsKey(cache.getIdentifier())) {
            throw new CacheNotFoundException(cache.getIdentifier());
        }

        return this.caches.get(cache.getIdentifier());
    }
}
