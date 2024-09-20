package com.github.ryanlaverick.framework.event;

import com.github.ryanlaverick.Skyblock;
import org.bukkit.configuration.file.FileConfiguration;

public final class BrokerManager {
    private Skyblock skyblock;

    public BrokerManager(Skyblock skyblock) {
        this.skyblock = skyblock;
    }

    public void establishConnection() {
        FileConfiguration amqpFile = this.skyblock.getFileSystemManager().getAMQPFile().getFileConfiguration();

        RabbitMqBroker broker = new RabbitMqBroker(
                amqpFile.getString("AMQP_HOST"),
                amqpFile.getString("AMQP_USERNAME"),
                amqpFile.getString("AMQP_PASSWORD"),
                amqpFile.getString("AMQP_VIRTUAL_HOST"),
                amqpFile.getInt("AMQP_PORT"),
                amqpFile.getString("AMQP_EXCHANGE_NAME")
        );
        broker.connect();

        this.skyblock.getLogger().info("Connection to RabbitMQ established successfully on queue: " + broker.getQueue());
    }
}
