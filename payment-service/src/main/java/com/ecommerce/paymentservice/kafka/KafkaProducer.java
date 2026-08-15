package com.ecommerce.paymentservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.ecommerce.paymentservice.event.PaymentCompletedEvent;

@Component
public class KafkaProducer {

    private static final String TOPIC = "payment-completed";

    private final KafkaTemplate<String, PaymentCompletedEvent> kafkaTemplate;

    public KafkaProducer(
            KafkaTemplate<String, PaymentCompletedEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    /*
     * Publish Payment Completed Event
     */
    public void publishPaymentCompleted(
            PaymentCompletedEvent event) {

        try {

            kafkaTemplate.send(
                    TOPIC,
                    event.getOrderId().toString(),
                    event).get();

            System.out.println(
                    "PaymentCompletedEvent Published Successfully");

            System.out.println(event);

        } catch (Exception ex) {

            System.out.println(
                    "Failed to Publish PaymentCompletedEvent");

            ex.printStackTrace();

        }

    }

}