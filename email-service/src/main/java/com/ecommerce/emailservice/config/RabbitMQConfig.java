package com.ecommerce.emailservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /*
     * Queue Name
     */
    public static final String QUEUE =
            "delivery.email.queue";

    /*
     * Exchange Name
     */
    public static final String EXCHANGE =
            "delivery.exchange";

    /*
     * Common Routing Key
     */
    public static final String ROUTING_KEY =
            "delivery.notification";

    /*
     * Email Queue
     */
    @Bean
    public Queue queue() {

        return new Queue(
                QUEUE,
                true);

    }

    /*
     * Direct Exchange
     */
    @Bean
    public TopicExchange  exchange() {

        return new TopicExchange (
                EXCHANGE);

    }

    /*
     * Queue Binding
     */
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