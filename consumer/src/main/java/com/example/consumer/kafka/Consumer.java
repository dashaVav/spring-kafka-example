package com.example.consumer.kafka;

import com.example.consumer.dto.PersonInfoDTO;
import com.fasterxml.jackson.databind.JsonNode;
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

    @KafkaListener(topics = "${kafka.topic.name.person-info}", groupId = "${kafka.group-id.name.person-info}")
    public void personInfoListener(ConsumerRecord<String, PersonInfoDTO> personInfo) {
        LOGGER.info("Message with the text \"" + personInfo.value()
                + "\" received from  kafka topic \"" + personInfo.topic() + "\"");
    }

    @KafkaListener(topics = "${kafka.topic.name.json}", groupId = "${kafka.group-id.name.json}")
    public void jsonInfoListener(ConsumerRecord<String, JsonNode> personInfo) {
        LOGGER.info("Message with the text \"" + personInfo.value()
                + "\" received from  kafka topic \"" + personInfo.topic() + "\"");
    }
}
