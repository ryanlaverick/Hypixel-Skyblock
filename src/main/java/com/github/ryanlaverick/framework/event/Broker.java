package com.github.ryanlaverick.framework.event;

import com.github.ryanlaverick.framework.event.exceptions.*;
import com.rabbitmq.client.Connection;

public interface Broker {
    void connect() throws BrokerConnectionAlreadyOpenException, BrokerConnectionTimeoutException, BrokerInvalidCredentialsException;
    void disconnect() throws BrokerConnectionNotOpenException, BrokerConnectionCouldNotBeClosedException;
    Connection getConnection();
}
