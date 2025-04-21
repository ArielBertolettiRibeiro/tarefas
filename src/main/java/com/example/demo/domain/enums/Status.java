package com.example.demo.domain.enums;

public enum Status {

    TO_DO(1, "A fazer"),
    IN_PROGRESS(2, "Em progresso"),
    DONE(3, "Feito");

    private int number;
    private String type;

    Status(int number, String type) {
        this.number = number;
        this.type = type;
    }
}
