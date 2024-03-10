package com.example.producer.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {
    @Value("${kafka.topic.name.person-info}")
    private String personInfoTopic;

    @Bean
    public NewTopic newPersonInfoTopic() {
        return new NewTopic(personInfoTopic, 1, (short) 1);
    }

    @Value("${kafka.topic.name.json}")
    private String jsonTopic;

    @Bean
    public NewTopic newJsonTopic() {
        return new NewTopic(jsonTopic, 1, (short) 1);
    }
}
