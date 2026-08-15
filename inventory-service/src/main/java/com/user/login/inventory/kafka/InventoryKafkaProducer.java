package com.user.login.inventory.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.user.login.inventory.event.InventoryUpdatedEvent;

@Component
public class InventoryKafkaProducer {

    private static final String TOPIC = "inventory-updated";

    private final KafkaTemplate<String, InventoryUpdatedEvent> kafkaTemplate;

    public InventoryKafkaProducer(KafkaTemplate<String, InventoryUpdatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishInventoryUpdated(InventoryUpdatedEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event.getOrderId().toString(),
                event);

        System.out.println("Inventory Updated Event Published");
        System.out.println(event);
    }
}