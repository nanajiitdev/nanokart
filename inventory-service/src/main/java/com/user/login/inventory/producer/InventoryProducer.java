package com.user.login.inventory.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.user.login.inventory.event.InventoryUpdatedEvent;

@Service
public class InventoryProducer {

    private final KafkaTemplate<String, InventoryUpdatedEvent> kafkaTemplate;

    public InventoryProducer(KafkaTemplate<String, InventoryUpdatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(InventoryUpdatedEvent event) {

        kafkaTemplate.send("inventory-updated", event);

        System.out.println("================================");
        System.out.println("Inventory Updated Event Published");
        System.out.println("Order Id : " + event.getOrderId());
        System.out.println("Product Id : " + event.getProductId());
        System.out.println("Remaining Qty : " + event.getAvailableQuantity());
        System.out.println("================================");
    }
}