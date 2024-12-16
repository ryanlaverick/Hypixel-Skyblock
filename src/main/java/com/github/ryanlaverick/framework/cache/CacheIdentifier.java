package com.github.ryanlaverick.framework.cache;

public enum CacheIdentifier {
    CONFIG("config");

    private final String identifier;

    CacheIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
