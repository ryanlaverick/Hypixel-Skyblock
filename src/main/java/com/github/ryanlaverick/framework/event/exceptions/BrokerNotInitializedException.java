package com.github.ryanlaverick.framework.event.exceptions;

public final class BrokerNotInitializedException extends RuntimeException {
    public BrokerNotInitializedException() {
        super("Broker module has not yet been initialized!");
    }
}
