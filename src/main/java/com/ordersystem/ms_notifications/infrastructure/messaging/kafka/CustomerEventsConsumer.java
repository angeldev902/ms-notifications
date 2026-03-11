package com.ordersystem.ms_notifications.infrastructure.messaging.kafka;

import com.ordersystem.ms_notifications.application.service.IdempotencyService;
import com.ordersystem.ms_notifications.domain.model.CustomerEvent;
import com.ordersystem.ms_notifications.application.handler.NotificationEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerEventsConsumer {

    private final IdempotencyService idempotencyService;
    private final NotificationEventHandler handler;

    @RetryableTopic(
            attempts = "3", // 1 intento inicial + 2 retries
            backoff = @Backoff(
                    delay = 3000,      // 3 segundos inicial
                    multiplier = 2.0   // exponencial
            ),
            autoCreateTopics = "true",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            dltTopicSuffix = "-dlt"
    )
    @KafkaListener(
            topics = "customers.events",
            groupId = "notifications-service"
    )
    public void consume(
            CustomerEvent event,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset
    ) {

        String eventId = topic + "-" + partition + "-" + offset;

        log.info("Processing eventId={}", eventId);

        if (idempotencyService.isProcessed(eventId)) {

            log.warn("Event already processed {}", eventId);

            return;
        }

        try {

            handler.handle(event);

            idempotencyService.markProcessed(eventId);

        } catch (Exception e) {

            log.error("Error processing event {}", eventId, e);

            throw e;
        }




    }



}
