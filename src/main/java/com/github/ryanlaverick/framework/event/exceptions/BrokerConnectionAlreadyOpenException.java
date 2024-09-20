package com.github.ryanlaverick.framework.event.exceptions;

public final class BrokerConnectionAlreadyOpenException extends RuntimeException {
    public BrokerConnectionAlreadyOpenException() {
        super("Unable to establish new connection to broker as the connection is already open!");
    }
}
