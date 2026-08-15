package com.ecommerce.paymentservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.paymentservice.event.PaymentCompletedEvent;

@Component
public class PaymentEventPublisherImpl implements PaymentEventPublisher {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void publishPaymentCompleted(PaymentCompletedEvent event) {

        kafkaProducer.publishPaymentCompleted(event);

    }
}