package com.ecommerce.paymentservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic paymentCompletedTopic() {

        return TopicBuilder
                .name("payment-completed")
                .partitions(4)
                .replicas(1)
                .build();

    }

	/*  commented out because we are using Spring Boot's auto-configuration for Kafka producer for docker
	 * @Bean public ProducerFactory<String, PaymentCompletedEvent> producerFactory()
	 * {
	 * 
	 * Map<String, Object> config = new HashMap<>();
	 * 
	 * config.put( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	 * 
	 * config.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	 * StringSerializer.class);
	 * 
	 * config.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	 * JsonSerializer.class);
	 * 
	 * return new DefaultKafkaProducerFactory<>(config);
	 * 
	 * }
	 * 
	 * @Bean public KafkaTemplate<String, PaymentCompletedEvent> kafkaTemplate() {
	 * 
	 * return new KafkaTemplate<>(producerFactory());
	 * 
	 * }
	 */

}