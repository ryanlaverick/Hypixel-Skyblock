package com.github.ryanlaverick.framework.event;

import com.github.ryanlaverick.Skyblock;
import com.github.ryanlaverick.framework.cache.Cache;
import com.github.ryanlaverick.framework.cache.CacheIdentifier;
import org.bukkit.configuration.file.FileConfiguration;

public final class BrokerManager {
    private Skyblock skyblock;
    private Broker broker;

    public BrokerManager(Skyblock skyblock) {
        this.skyblock = skyblock;
    }

    public void establishConnection() {
        Cache config = this.skyblock.getCacheManager().getCache(CacheIdentifier.CONFIG);

        this.broker = new RabbitMqBroker(
                config.getString("AMQP_HOST"),
                config.getString("AMQP_USERNAME"),
                config.getString("AMQP_PASSWORD"),
                config.getString("AMQP_VIRTUAL_HOST"),
                config.getInt("AMQP_PORT"),
                config.getString("AMQP_EXCHANGE_NAME")
        );

        try {
            this.broker.connect();
        } catch (Throwable throwable) {
            this.skyblock.getLogger().severe("[AMQP] Connection to event broker could not be established!");
            this.skyblock.getLogger().severe("[AMQP] Context: " + throwable.getMessage());
        } finally {
            this.skyblock.getLogger().info("[AMQP] Connection to event broker established successfully!");
        }
    }

    public void closeConnection() {
        try {
            this.broker.disconnect();
        } catch (Throwable throwable) {
            this.skyblock.getLogger().severe("[AMQP] Connection to event broker could not be disconnected!");
            this.skyblock.getLogger().severe("[AMQP] Context: " + throwable.getMessage());
        } finally {
            this.skyblock.getLogger().info("[AMQP] Connection to event broker closed successfully!");
        }
    }
}
