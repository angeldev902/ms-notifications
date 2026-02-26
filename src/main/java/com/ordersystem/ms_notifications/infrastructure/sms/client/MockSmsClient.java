package com.ordersystem.ms_notifications.infrastructure.sms.client;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Profile("dev") // only development
public class MockSmsClient implements SmsClient {
    private static final Logger log = LoggerFactory.getLogger(MockSmsClient.class);

    private final Random random = new Random();

    @Override
    public void send(String phone, String message) {

        simulateLatency();

        if (shouldFail()) {
            log.error("SMS FAILED phone={} message={}", phone, message);
            throw new RuntimeException("Simulated SMS failure");
        }

        log.info("SMS SENT phone={} message={}", phone, message);
    }

    private void simulateLatency() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {}
    }

    private boolean shouldFail() {
        return random.nextInt(10) < 2; // 20% failure rate
    }

}
