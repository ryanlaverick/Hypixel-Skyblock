package com.github.ryanlaverick.framework.cache;

import com.github.ryanlaverick.framework.cache.exceptions.CacheKeyAlreadyUsedException;
import com.github.ryanlaverick.framework.cache.exceptions.CacheKeyNotFoundException;

import java.util.HashMap;
import java.util.Map;

public final class Cache {
    private final Map<String, Object> cache;

    public Cache() {
        this.cache = new HashMap<>();
    }

    public Map<String, Object> getCache() {
        return cache;
    }

    public void add(String key, Object value) throws CacheKeyAlreadyUsedException {
        if (this.has(key)) {
            throw new CacheKeyAlreadyUsedException(key);
        }

        this.cache.put(key, value);
    }

    public void remove(String key) throws CacheKeyNotFoundException {
        if (!this.has(key)) {
            throw new CacheKeyNotFoundException(key);
        }

        this.cache.remove(key);
    }

    public boolean has(String key) {
        return this.cache.containsKey(key);
    }

    public Object get(String key) throws CacheKeyNotFoundException {
        return this.cache.get(key);
    }

    public String getString(String key) throws CacheKeyNotFoundException {
        return (String) this.get(key);
    }

    public int getInt(String key) throws CacheKeyNotFoundException {
        return (int) this.get(key);
    }

    public void clear() {
        this.cache.clear();
    }
}
