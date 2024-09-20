package com.github.ryanlaverick.framework.event;

import com.github.ryanlaverick.Skyblock;
import org.bukkit.configuration.file.FileConfiguration;

public final class BrokerManager {
    private Skyblock skyblock;
    private Broker broker;

    public BrokerManager(Skyblock skyblock) {
        this.skyblock = skyblock;
    }

    public void establishConnection() {
        FileConfiguration amqpFile = this.skyblock.getFileSystemManager().getAMQPFile().getFileConfiguration();

        this.broker = new RabbitMqBroker(
                amqpFile.getString("AMQP_HOST"),
                amqpFile.getString("AMQP_USERNAME"),
                amqpFile.getString("AMQP_PASSWORD"),
                amqpFile.getString("AMQP_VIRTUAL_HOST"),
                amqpFile.getInt("AMQP_PORT"),
                amqpFile.getString("AMQP_EXCHANGE_NAME")
        );
        this.broker.connect();

        this.skyblock.getLogger().info("[AMQP] Connection to event broker established successfully!");
    }

    public void closeConnection() {
        this.broker.disconnect();

        this.skyblock.getLogger().info("[AMQP] Connection to event broker closed successfully!");
    }
}
