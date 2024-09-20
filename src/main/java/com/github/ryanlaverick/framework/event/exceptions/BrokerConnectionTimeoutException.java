package com.github.ryanlaverick.framework.event.exceptions;

public final class BrokerConnectionTimeoutException extends RuntimeException {
    public BrokerConnectionTimeoutException() {
        super("Establishing new connection to broker has timed out!");
    }
}
