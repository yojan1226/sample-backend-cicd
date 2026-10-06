package com.example.samplebackend.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthControllerTest {

    @Test
    void healthStatusShouldBeUp() {
        HealthController controller = new HealthController();

        assertEquals("UP", controller.health().get("status"));
        assertEquals("sample-backend", controller.health().get("service"));
    }

    @Test
    void messageShouldBePresent() {
        HealthController controller = new HealthController();

        assertEquals("Hello from Sample Backend", controller.message().get("message"));
    }
}
