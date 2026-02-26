package com.ordersystem.ms_notifications.consumer;

import com.ordersystem.ms_notifications.domain.CustomerEvent;
import com.ordersystem.ms_notifications.handler.NotificationEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
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
    public void consume(CustomerEvent event, Acknowledgment acknowledgment) {

        log.info("Event received from Kafka: {}", event);

        try {
            handler.handle(event);

            // Confirm offset only everything went well
            acknowledgment.acknowledge();

        } catch (Exception e) {
            log.error("Error processing event. Offset will not be committed", e);
            throw e;
        }
    }

}
