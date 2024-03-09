package com.example.consumer.dto;

import java.util.List;

public record PersonInfoDTO(
        String name,
        int age,
        List<String> children,
        boolean married,
        String dog,
        PersonShortInfo mother
) {
}
