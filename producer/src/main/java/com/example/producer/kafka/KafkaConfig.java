package com.example.producer.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Value("${kafka.topic.name}")
    private String ageTopic;

    @Bean
    public NewTopic newTopic() {
        return new NewTopic(ageTopic, 1, (short) 1);
    }
}
