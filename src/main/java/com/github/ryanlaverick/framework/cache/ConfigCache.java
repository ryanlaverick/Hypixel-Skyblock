package com.github.ryanlaverick.framework.cache;

public final class ConfigCache extends Cache {
    @Override
    public String getIdentifier() {
        return CacheIdentifier.CONFIG.getIdentifier();
    }
}
