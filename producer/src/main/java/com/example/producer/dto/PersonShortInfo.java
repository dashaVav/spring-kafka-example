package com.example.producer.dto;

public record PersonShortInfo(
        String name,
        int age) {
    public PersonShortInfo withAge(int newAge) {
        return new PersonShortInfo(name, newAge);
    }
}