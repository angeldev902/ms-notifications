package com.ordersystem.ms_notifications.infrastructure.persistence.repository;

import com.ordersystem.ms_notifications.infrastructure.persistence.entity.ProcessedEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProcessedEventRepository extends JpaRepository<ProcessedEventEntity, String> {
}
