package com.user.delivery.config;


import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /*
     * Exchange Name
     */
    public static final String EXCHANGE =
            "delivery.exchange";

    /*
     * Common Routing Key
     *
     * SMS Queue
     * Email Queue
     * WhatsApp Queue
     * Push Queue
     *
     * All services will bind using this routing key.
     */
    public static final String ROUTING_KEY =
            "delivery.notification";

    /*
     * Direct Exchange
     */
    @Bean
    public TopicExchange  exchange() {

        return new TopicExchange(
                EXCHANGE,
                true,
                false);

    }

    /*
     * JSON Message Converter
     */
    @Bean
    public MessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();

    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter) {

        RabbitTemplate rabbitTemplate =
                new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(messageConverter);

        // IMPORTANT
        rabbitTemplate.setObservationEnabled(true);

        return rabbitTemplate;
    }

}