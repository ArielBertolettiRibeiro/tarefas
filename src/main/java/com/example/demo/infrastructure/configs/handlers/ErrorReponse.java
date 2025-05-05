package com.example.demo.infrastructure.configs.handlers;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorReponse (
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<String> details
){}
