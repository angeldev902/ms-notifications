package com.ordersystem.ms_notifications.domain.port.output;

public interface ProcessedEventRepositoryPort {
    boolean existsById(String eventId);

    void save(String eventId);
}