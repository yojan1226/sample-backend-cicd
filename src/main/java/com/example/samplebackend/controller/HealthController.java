package com.example.samplebackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "service", "sample-backend"
        );
    }

    @GetMapping("/api/message")
    public Map<String, String> message() {
        return Map.of(
                "message", "Hello from Sample Backend"
        );
    }
}
