package com.ecommerce.smsservice.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.smsservice.model.DeliveryNotification;



@Configuration
public class RabbitMQJsonConfig {

    @Bean
    public MessageConverter messageConverter() {

        Jackson2JsonMessageConverter converter =
                new Jackson2JsonMessageConverter();

        DefaultJackson2JavaTypeMapper typeMapper =
                new DefaultJackson2JavaTypeMapper();

        Map<String, Class<?>> mapping = new HashMap<>();

        mapping.put(
                "com.user.delivery.model.DeliveryNotification",
                DeliveryNotification.class
        );

        typeMapper.setIdClassMapping(mapping);

        converter.setJavaTypeMapper(typeMapper);

        return converter;
    }
}