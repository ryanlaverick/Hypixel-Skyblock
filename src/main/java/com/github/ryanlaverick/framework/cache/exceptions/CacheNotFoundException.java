package com.github.ryanlaverick.framework.cache.exceptions;

public final class CacheNotFoundException extends RuntimeException {
    public CacheNotFoundException(String key) {
        super("Cache with identifier `" + key + "` not found!");
    }
}
