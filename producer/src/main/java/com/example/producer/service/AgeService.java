package com.example.producer.service;

import com.example.producer.dto.PersonInfoDTO;
import com.example.producer.kafka.Producer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgeService {
    private final Producer kafkaProducer;

    public PersonInfoDTO changeAge(PersonInfoDTO personInfo) {
        PersonInfoDTO changedPersonInfo = personInfo.withAge(96);
        kafkaProducer.sendMessage(changedPersonInfo);
        return changedPersonInfo;
    }

    public JsonNode changeAge(JsonNode personInfo) {
        changeAgeRecursively((ObjectNode) personInfo);
        kafkaProducer.sendJson(personInfo);
        return personInfo;
    }

    private void changeAgeRecursively(ObjectNode node) {
        node.fields().forEachRemaining(entry -> {
            if (entry.getKey().equals("age")) {
                entry.setValue(IntNode.valueOf(96));
            } else if (entry.getValue().isObject()) {
                changeAgeRecursively((ObjectNode) entry.getValue());
            } else if (entry.getValue().isArray()) {
                entry.getValue().forEach(item -> {
                    if (item.isObject()) {
                        changeAgeRecursively((ObjectNode) item);
                    }
                });
            }
        });
    }
}
