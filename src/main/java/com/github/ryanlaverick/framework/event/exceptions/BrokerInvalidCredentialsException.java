package com.github.ryanlaverick.framework.event.exceptions;

public final class BrokerInvalidCredentialsException extends RuntimeException {
    public BrokerInvalidCredentialsException() {
        super("Unable to establish new connection to broker as the credentials are invalid!");
    }
}
