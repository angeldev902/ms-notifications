package com.ordersystem.ms_notifications.infrastructure.persistence.adapter;

import com.ordersystem.ms_notifications.domain.port.output.ProcessedEventRepositoryPort;
import com.ordersystem.ms_notifications.infrastructure.persistence.entity.ProcessedEventEntity;
import com.ordersystem.ms_notifications.infrastructure.persistence.repository.JpaProcessedEventRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ProcessedEventRepositoryAdapter implements ProcessedEventRepositoryPort {
    private final JpaProcessedEventRepository repository;

    public ProcessedEventRepositoryAdapter(JpaProcessedEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsById(String eventId) {
        return repository.existsById(eventId);
    }

    @Override
    public void save(String eventId) {
        ProcessedEventEntity entity = new ProcessedEventEntity(eventId, Instant.now());

        repository.save(entity);
    }
}
