package com.github.ryanlaverick.framework.cache.exceptions;

public final class CacheKeyNotFoundException extends RuntimeException {
    public CacheKeyNotFoundException(String key) {
        super("Cache item with key `" + key + "` not found!");
    }
}
