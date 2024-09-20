package com.github.ryanlaverick.framework.event;

import com.github.ryanlaverick.framework.event.exceptions.BrokerConnectionAlreadyOpenException;
import com.github.ryanlaverick.framework.event.exceptions.BrokerConnectionTimeoutException;
import com.github.ryanlaverick.framework.event.exceptions.BrokerInvalidCredentialsException;
import com.rabbitmq.client.Connection;

public interface Broker {
    void connect() throws BrokerConnectionAlreadyOpenException, BrokerConnectionTimeoutException, BrokerInvalidCredentialsException;
    void disconnect();
    Connection getConnection();
}
