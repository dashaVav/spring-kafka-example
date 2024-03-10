package com.example.producer.kafka;

import com.example.producer.dto.PersonInfoDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final static Logger LOGGER = LogManager.getLogger();

    @Value("${kafka.topic.name.person-info}")
    private String personInfoTopic;


    @Value("${kafka.topic.name.json}")
    private String jsonTopic;

    public void sendMessage(PersonInfoDTO personInfo) {
        kafkaTemplate.send(personInfoTopic, personInfo);
        LOGGER.info("Message with the text \"" + personInfo + "\" added to  kafka topic \"" + personInfoTopic + "\"");
    }

    public void sendJson(JsonNode personInfo) {
        kafkaTemplate.send(jsonTopic, personInfo);
        LOGGER.info("Message with the text \"" + personInfo + "\" added to  kafka topic \"" + jsonTopic + "\"");

    }
}
