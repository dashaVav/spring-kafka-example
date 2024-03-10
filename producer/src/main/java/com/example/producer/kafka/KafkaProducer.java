package com.example.producer.kafka;

import com.example.producer.dto.PersonInfoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, PersonInfoDTO> kafkaTemplate;
    private final static Logger LOGGER = LogManager.getLogger();

    @Value("${kafka.topic.name}")
    private String ageTopic;

//    @Value("${kafka.topic.name}")
    private String ageTopic2 = "json";

    public void sendMessage(PersonInfoDTO personInfo) {
        try {
            String person = objectMapper.writeValueAsString(personInfo);
            kafkaTemplate.send(ageTopic, personInfo);
            LOGGER.info("Message" + person + "добавлено в kafka");
        } catch (Exception ignored) {

        }
    }

    public void sendJson(String string) {
//        try {
//            kafkaTemplate.send(ageTopic2, string);
//            LOGGER.info("Message" + string + "добавлено в kafka");
//        } catch (Exception ignored) {
//
//        }
    }
}
