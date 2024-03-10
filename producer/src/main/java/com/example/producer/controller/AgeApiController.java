package com.example.producer.controller;

import com.example.producer.dto.PersonInfoDTO;
import com.example.producer.service.AgeService;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AgeApiController {
    private final AgeService ageService;

    @PostMapping("/age/person-info")
    public ResponseEntity<PersonInfoDTO> changeAge(@RequestBody PersonInfoDTO personInfo) {
        return ResponseEntity.ok(ageService.changeAge(personInfo));
    }

    @PostMapping("/age/json")
    public ResponseEntity<JsonNode> changeAge(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    schema = @Schema(
                            example = """
                                    {
                                      "name": "Nick",
                                      "age": 45,
                                      "children": [
                                        {
                                          "name": "Elena",
                                          "age": 12
                                        }
                                      ],
                                      "married": true,
                                      "dog": "Leo",
                                      "mother": {
                                        "name": "Mary",
                                        "age": 65
                                      }
                                    }""")
            )
    )
                                              @RequestBody JsonNode personInfo) {
        return ResponseEntity.ok(ageService.changeAge(personInfo));
    }
}
