package com.example.producer.kafka;

import com.example.producer.dto.PersonInfoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String ageTopic;

    public void sendMessage(PersonInfoDTO personInfo) {
        try {
            String person = objectMapper.writeValueAsString(personInfo);
            kafkaTemplate.send(ageTopic, person);
        } catch (Exception ignored) {

        }

    }
}
