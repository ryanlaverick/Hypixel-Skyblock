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
        this.broker = new RabbitMqBroker(
                this.skyblock.getCache().getString("AMQP_HOST"),
                this.skyblock.getCache().getString("AMQP_USERNAME"),
                this.skyblock.getCache().getString("AMQP_PASSWORD"),
                this.skyblock.getCache().getString("AMQP_VIRTUAL_HOST"),
                this.skyblock.getCache().getInt("AMQP_PORT"),
                this.skyblock.getCache().getString("AMQP_EXCHANGE_NAME")
        );
        this.broker.connect();

        this.skyblock.getLogger().info("[AMQP] Connection to event broker established successfully!");
    }

    public void closeConnection() {
        this.broker.disconnect();

        this.skyblock.getLogger().info("[AMQP] Connection to event broker closed successfully!");
    }
}
