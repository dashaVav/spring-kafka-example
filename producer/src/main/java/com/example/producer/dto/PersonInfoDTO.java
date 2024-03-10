package com.example.producer.dto;

import java.util.List;

public record PersonInfoDTO(
        String name,
        int age,
        List<String> children,
        boolean married,
        String dog,
        PersonShortInfo mother
) {
    public PersonInfoDTO withAge(int newAge) {
        return new PersonInfoDTO(name, newAge, children, married, dog, mother.withAge(newAge));
    }
}
