package com.ordersystem.ms_notifications.infrastructure.messaging.kafka;

import com.ordersystem.ms_notifications.domain.model.CustomerEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerEventsDltConsumer {

    @KafkaListener(
            topics = "customers.events-dlt",
            groupId = "notifications-service-dlt"
    )
    public void listenDlt(CustomerEvent event) {

        log.error("Event moved to DLT after retries exhausted: {}", event);

        // - Here you could notify the ops team
        // - Send Slack alert
        // - Persist error for later reprocessing
        // - Trigger monitoring system alert

    }

}
