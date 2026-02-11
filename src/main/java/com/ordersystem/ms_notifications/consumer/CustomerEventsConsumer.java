package com.ordersystem.ms_notifications.consumer;

import com.ordersystem.ms_notifications.domain.CustomerEvent;
import com.ordersystem.ms_notifications.handler.NotificationEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerEventsConsumer {

    private final NotificationEventHandler handler;

    @KafkaListener(
            topics = "customers.events",
            groupId = "notifications-service"
    )
    public void consume(CustomerEvent event) {

        log.info("Event received from Kafka: {}", event);

        handler.handle(event);
    }

}
