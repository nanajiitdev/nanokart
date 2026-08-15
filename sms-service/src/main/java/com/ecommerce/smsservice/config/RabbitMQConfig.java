package com.ecommerce.smsservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE =
            "delivery.sms.queue";

    public static final String EXCHANGE =
            "delivery.exchange";

    public static final String ROUTING_KEY =
            "delivery.notification";

    @Bean
    public Queue queue() {

        return new Queue(
                QUEUE,
                true);

    }

    @Bean
    public TopicExchange  exchange() {

        return new TopicExchange(
                EXCHANGE);

    }

    @Bean
    public Binding binding() {

        return BindingBuilder

                .bind(queue())

                .to(exchange())

                .with(ROUTING_KEY);

    }

    @Bean
    public SimpleRabbitListenerContainerFactory
            rabbitListenerContainerFactory(
                    ConnectionFactory connectionFactory,
                    MessageConverter messageConverter) {

        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);

        factory.setMessageConverter(messageConverter);

        // Important for tracing
        factory.setObservationEnabled(true);

        return factory;
    }
    
}