package com.ordersystem.ms_notifications.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CustomerEvent {

    private String eventType;
    private Long customerId;
    private BigDecimal occurredAt;

    public Instant getOccurredAtAsInstant() {

        long seconds = occurredAt.longValue();
        long nanos = occurredAt
                .subtract(BigDecimal.valueOf(seconds))
                .movePointRight(9)
                .longValue();

        return Instant.ofEpochSecond(seconds, nanos);
    }

}
