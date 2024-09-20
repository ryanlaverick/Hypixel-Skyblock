package com.github.ryanlaverick.framework.event.exceptions;

public final class BrokerConnectionNotOpenException extends RuntimeException {
    public BrokerConnectionNotOpenException() {
        super("Connection to broker is not currently open!");
    }
}
