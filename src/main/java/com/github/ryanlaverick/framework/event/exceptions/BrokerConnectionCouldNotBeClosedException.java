package com.github.ryanlaverick.framework.event.exceptions;

public final class BrokerConnectionCouldNotBeClosedException extends RuntimeException {
    public BrokerConnectionCouldNotBeClosedException() {
        super("Connection to broker could not be gracefully closed!");
    }
}
