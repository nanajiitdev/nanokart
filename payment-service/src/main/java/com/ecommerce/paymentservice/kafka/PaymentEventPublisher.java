package com.ecommerce.paymentservice.kafka;

import com.ecommerce.paymentservice.event.PaymentCompletedEvent;

public interface PaymentEventPublisher {

    void publishPaymentCompleted(PaymentCompletedEvent event);

}