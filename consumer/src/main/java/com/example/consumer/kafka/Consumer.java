package com.example.consumer.kafka;

import com.example.consumer.dto.PersonInfoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Consumer {
    private final static Logger LOGGER = LogManager.getLogger();
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic.name.person-info}", groupId = "${kafka.group-id.name.person-info}")
    public void personInfoListener(ConsumerRecord<String, String> personInfo) {
        try {
            PersonInfoDTO person = objectMapper.readValue(personInfo.value(), PersonInfoDTO.class);
            LOGGER.info("Message with the text \"" + person
                    + "\" received from  kafka topic \"" + personInfo.topic() + "\"");
        } catch (JsonProcessingException e) {
            LOGGER.warn("Problem with mapping message\"" + personInfo.value()
                    + "\" received from  kafka topic \"" + personInfo.topic() + "\"");
        }
    }

    @KafkaListener(topics = "${kafka.topic.name.json}", groupId = "${kafka.group-id.name.json}")
    public void jsonInfoListener(ConsumerRecord<String, String> personInfo) {
        try {
            JsonNode person = objectMapper.readValue(personInfo.value(), JsonNode.class);
            LOGGER.info("Message with the text \"" + person
                    + "\" received from  kafka topic \"" + personInfo.topic() + "\"");
        } catch (JsonProcessingException e) {
            LOGGER.warn("Problem with mapping message\"" + personInfo.value()
                    + "\" received from  kafka topic \"" + personInfo.topic() + "\"");
        }
    }
}
