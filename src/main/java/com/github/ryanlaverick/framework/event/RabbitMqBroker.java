package com.github.ryanlaverick.framework.event;

import com.github.ryanlaverick.Skyblock;
import com.github.ryanlaverick.framework.event.exceptions.BrokerConnectionAlreadyOpenException;
import com.github.ryanlaverick.framework.event.exceptions.BrokerConnectionNotOpenException;
import com.github.ryanlaverick.framework.event.exceptions.BrokerConnectionTimeoutException;
import com.github.ryanlaverick.framework.event.exceptions.BrokerInvalidCredentialsException;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public final class RabbitMqBroker implements Broker {
    private Skyblock skyblock;

    private Connection connection;
    private Channel channel;
    private String queue;

    public RabbitMqBroker(Skyblock skyblock) {
        this.skyblock = skyblock;
    }

    @Override
    public void connect() throws BrokerConnectionAlreadyOpenException, BrokerInvalidCredentialsException, BrokerConnectionTimeoutException {
        if (this.connection != null) {
            throw new BrokerConnectionAlreadyOpenException();
        }

        try {
            ConnectionFactory factory = new ConnectionFactory();

            factory.setHost("localhost");
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
            factory.setPort(5672);

            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare("test-exchange", "headers");

            queue = channel.queueDeclare().getQueue();
            this.skyblock.getLogger().info("Connected to RabbitMQ on queue ".concat(this.queue));
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
}
