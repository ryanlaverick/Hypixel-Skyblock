package com.github.ryanlaverick.framework.event;

import com.github.ryanlaverick.Skyblock;
import com.github.ryanlaverick.framework.event.exceptions.BrokerConnectionAlreadyOpenException;

public final class BrokerManager {
    private Skyblock skyblock;
    private Broker broker;

    public BrokerManager(Skyblock skyblock) {
        this.skyblock = skyblock;
    }

    public void establishConnection() {
        this.broker = new RabbitMqBroker(this.skyblock);
        this.broker.connect();
    }
}
