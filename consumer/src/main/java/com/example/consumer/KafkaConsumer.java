package com.example.consumer;

import com.example.consumer.dto.PersonInfoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "my_consumer")
    public void listen(String personInfo) {
        try {
            PersonInfoDTO person = objectMapper.readValue(personInfo, PersonInfoDTO.class);
            System.out.println("receive" + person);
        } catch (Exception ignored) {

        }
    }
}
