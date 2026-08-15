package com.ecommerce.emailservice.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ecommerce.emailservice.config.RabbitMQConfig;
import com.ecommerce.emailservice.model.DeliveryNotification;
import com.ecommerce.emailservice.service.EmailService;

@Component
public class NotificationConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(NotificationConsumer.class);

    private final EmailService emailService;

    public NotificationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(
            queues = RabbitMQConfig.QUEUE,
            containerFactory = "rabbitListenerContainerFactory")
    public void consume(DeliveryNotification notification) {

        log.info("========== EMAIL RABBITMQ CONSUMER ==========");
        log.info("Notification received: {}", notification);

        emailService.sendEmail(notification);

        log.info("Email Sent Successfully");
    }
}

