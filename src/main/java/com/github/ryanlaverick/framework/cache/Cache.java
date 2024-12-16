package com.github.ryanlaverick.framework.cache;

import com.github.ryanlaverick.framework.cache.exceptions.CacheKeyAlreadyUsedException;
import com.github.ryanlaverick.framework.cache.exceptions.CacheKeyNotFoundException;

import java.util.HashMap;
import java.util.Map;

public abstract class Cache {
    protected final Map<String, Object> cacheEntries;

    protected Cache() {
        this.cacheEntries = new HashMap<>();
    }

    public abstract String getIdentifier();

    public void add(String key, Object value) throws CacheKeyAlreadyUsedException {
        if (this.has(key)) {
            throw new CacheKeyAlreadyUsedException(key);
        }

        this.cacheEntries.put(key, value);
    }

    public void remove(String key) throws CacheKeyNotFoundException {
        if (!this.has(key)) {
            throw new CacheKeyNotFoundException(key);
        }

        this.cacheEntries.remove(key);
    }

    public void clear() {
        this.cacheEntries.clear();
    }

    public boolean has(String key) {
        return this.cacheEntries.containsKey(key);
    }

    public Object get(String key) throws CacheKeyNotFoundException {
        return this.cacheEntries.get(key);
    }

    public String getString(String key) throws CacheKeyNotFoundException {
        return (String) this.get(key);
    }

    public int getInt(String key) throws CacheKeyNotFoundException {
        return (int) this.get(key);
    }

    public int size() {
        return this.cacheEntries.size();
    }
}
