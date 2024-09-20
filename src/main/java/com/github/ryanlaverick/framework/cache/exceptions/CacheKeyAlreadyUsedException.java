package com.github.ryanlaverick.framework.cache.exceptions;

public final class CacheKeyAlreadyUsedException extends RuntimeException {
    public CacheKeyAlreadyUsedException(String key) {
        super("Cache item with key `" + key + "` already exists!");
    }
}
