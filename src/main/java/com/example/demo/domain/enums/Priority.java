package com.example.demo.domain.enums;

public enum Priority {

    LOW(1, "baixa"),
    MEDIUM(2, "media"),
    HIGH(3, "alta");

    private int number;
    private String type;

    Priority(int number, String type) {
        this.number = number;
        this.type = type;
    }
}
