package com.ordersystem.ms_notifications.infrastructure.messaging.kafka;

import com.ordersystem.ms_notifications.domain.model.CustomerEvent;
import com.ordersystem.ms_notifications.application.handler.NotificationEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerEventsConsumer {

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
    public void consume(CustomerEvent event) {

        log.info("Event received from Kafka: {}", event);

        handler.handle(event);

        log.info("Event processed successfully: {}", event.getCustomerId());
    }



}
