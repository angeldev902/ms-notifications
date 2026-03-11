package com.ordersystem.ms_notifications.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "processed_events")
public class ProcessedEventEntity {

    @Id
    private String eventId;

    private Instant processedAt;

    public ProcessedEventEntity() {}

    public ProcessedEventEntity(String eventId, Instant processedAt) {
        this.eventId = eventId;
        this.processedAt = processedAt;
    }

    public String getEventId() {
        return eventId;
    }

    public Instant getProcessedAt() {
        return processedAt;
    }
}