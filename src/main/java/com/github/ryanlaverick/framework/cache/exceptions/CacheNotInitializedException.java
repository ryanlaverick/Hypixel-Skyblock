package com.github.ryanlaverick.framework.cache.exceptions;

public final class CacheNotInitializedException extends RuntimeException {
    public CacheNotInitializedException() {
        super("Cache module has not yet been initialized!");
    }
}
