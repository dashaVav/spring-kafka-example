package com.example.producer.controller;

import com.example.producer.dto.PersonInfoDTO;
import com.example.producer.kafka.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Controller {
    private final KafkaProducer kafkaProducer;

    @PostMapping("/post")
    public ResponseEntity<Void> send(@RequestBody PersonInfoDTO person) {
        kafkaProducer.sendMessage(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
