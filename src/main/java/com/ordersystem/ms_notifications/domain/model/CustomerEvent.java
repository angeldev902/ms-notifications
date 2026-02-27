package com.ordersystem.ms_notifications.domain;

import com.ordersystem.ms_notifications.domain.model.NotificationType;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CustomerEvent {

    private NotificationType eventType;
    private Long customerId;
    private Instant occurredAt;
    private String name;
    private String email;
    private String phone;

}
