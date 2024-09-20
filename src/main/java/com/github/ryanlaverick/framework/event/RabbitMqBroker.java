package com.github.ryanlaverick.framework.event;

import com.github.ryanlaverick.framework.event.exceptions.BrokerConnectionAlreadyOpenException;
import com.github.ryanlaverick.framework.event.exceptions.BrokerConnectionNotOpenException;
import com.github.ryanlaverick.framework.event.exceptions.BrokerConnectionTimeoutException;
import com.github.ryanlaverick.framework.event.exceptions.BrokerInvalidCredentialsException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public final class RabbitMqBroker implements Broker {
    private Connection connection;
    private Channel channel;
    private String queue;

    private final String host;
    private final String username;
    private final String password;
    private final String virtualHost;
    private final int port;
    private final String exchangeName;

    public RabbitMqBroker(String host, String username, String password, String virtualHost, int port, String exchangeName) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.virtualHost = virtualHost;
        this.port = port;
        this.exchangeName = exchangeName;
    }

    @Override
    public void connect() throws BrokerConnectionAlreadyOpenException, BrokerInvalidCredentialsException, BrokerConnectionTimeoutException {
        if (this.connection != null) {
            throw new BrokerConnectionAlreadyOpenException();
        }

        try {
            ConnectionFactory factory = new ConnectionFactory();

            factory.setHost(this.host);
            factory.setUsername(this.username);
            factory.setPassword(this.password);
            factory.setVirtualHost(this.virtualHost);
            factory.setPort(this.port);

            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(this.exchangeName, "headers");

            queue = channel.queueDeclare().getQueue();
        } catch (IOException ignored) {
            throw new BrokerInvalidCredentialsException();
        } catch (TimeoutException ignored) {
            throw new BrokerConnectionTimeoutException();
        }
    }

    @Override
    public void disconnect() {
        if (this.connection == null) {
            throw new BrokerConnectionNotOpenException();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getQueue() {
        return queue;
    }
}
