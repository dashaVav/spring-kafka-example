package com.example.consumer.kafka;

import com.example.consumer.dto.PersonInfoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaListeners {
    private final ObjectMapper objectMapper;
    private final static Logger LOGGER = LogManager.getLogger();

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "my_consumer")
    public void listen(ConsumerRecord<String, PersonInfoDTO> personInfo) {
        try {
//            PersonInfoDTO person = objectMapper.readValue(personInfo, PersonInfoDTO.class);
            LOGGER.info("receive message:" + personInfo.value());
        } catch (Exception ignored) {

        }
    }

    @KafkaListener(topics = "json", groupId = "my_consumer")
    public void listen2(String personInfo) {
        try {
            LOGGER.info("receive message:" + personInfo);
        } catch (Exception ignored) {

        }
    }
}
