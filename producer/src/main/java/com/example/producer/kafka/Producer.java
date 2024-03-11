package com.example.producer.kafka;

import com.example.producer.dto.PersonInfoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Producer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final static Logger LOGGER = LogManager.getLogger();

    @Value("${kafka.topic.name.person-info}")
    private String personInfoTopic;

    @Value("${kafka.topic.name.json}")
    private String jsonTopic;

    public void sendMessage(PersonInfoDTO personInfo) {
        try {
            kafkaTemplate.send(personInfoTopic, UUID.randomUUID().toString(), objectMapper.writeValueAsString(personInfo));
            LOGGER.info("Message with the text \"" + personInfo + "\" added to  kafka topic \"" + personInfoTopic + "\"");
        } catch (JsonProcessingException ignore) {
        }
    }

    public void sendJson(JsonNode personInfo) {
        try {
            kafkaTemplate.send(jsonTopic, UUID.randomUUID().toString(), objectMapper.writeValueAsString(personInfo));
            LOGGER.info("Message with the text \"" + personInfo + "\" added to  kafka topic \"" + jsonTopic + "\"");
        } catch (JsonProcessingException ignore) {
        }
    }
}
