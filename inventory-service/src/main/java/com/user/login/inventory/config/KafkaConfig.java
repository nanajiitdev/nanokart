package com.user.login.inventory.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic inventoryUpdateTopic() {

        return TopicBuilder.name("inventory-updated")
                .partitions(5)
                .replicas(1)
                .build();
    }

	/*
	 * @Bean public ProducerFactory<String, InventoryUpdatedEvent> producerFactory()
	 * {
	 * 
	 * Map<String, Object> config = new HashMap<>();
	 * 
	 * config.put( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092" );
	 * 
	 * config.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	 * StringSerializer.class );
	 * 
	 * config.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	 * JsonSerializer.class );
	 * 
	 * return new DefaultKafkaProducerFactory<>(config); }
	 * 
	 * @Bean public KafkaTemplate<String, InventoryUpdatedEvent> kafkaTemplate() {
	 * 
	 * KafkaTemplate<String, InventoryUpdatedEvent> template = new
	 * KafkaTemplate<>(producerFactory());
	 * 
	 * // Enable Micrometer tracing for Kafka producer
	 * template.setObservationEnabled(true);
	 * 
	 * return template; }
	 */
}