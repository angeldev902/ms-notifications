package com.ordersystem.ms_notifications.application.service;

import com.ordersystem.ms_notifications.domain.port.output.ProcessedEventRepositoryPort;
import org.springframework.stereotype.Service;

// application NO depende de JPA
@Service
public class IdempotencyService {
    private final ProcessedEventRepositoryPort repository;

    public IdempotencyService(ProcessedEventRepositoryPort repository) {
        this.repository = repository;
    }

    public boolean isProcessed(String eventId) {
        return repository.existsById(eventId);
    }

    public void markProcessed(String eventId) {
        repository.save(eventId);
    }
}
