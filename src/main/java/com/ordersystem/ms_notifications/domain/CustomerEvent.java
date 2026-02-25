package com.ordersystem.ms_notifications.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CustomerEvent {

    private String eventType;
    private Long customerId;
    private Instant occurredAt;
    private String name;
    private String email;
    private String phone;

}
