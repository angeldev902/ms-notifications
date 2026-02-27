package com.ordersystem.ms_notifications.handler;

import com.ordersystem.ms_notifications.domain.model.CustomerEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.ordersystem.ms_notifications.application.*;
import com.ordersystem.ms_notifications.domain.model.NotificationType;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationEventHandler {

    private final SendWelcomeNotificationUseCase welcomeUseCase;
    private final SendCustomerUpdatedNotificationUseCase updatedUseCase;
    private final SendCustomerDeletedNotificationUseCase deletedUseCase;

    public void handle(CustomerEvent event) {

        switch (event.getEventType()) {

            case  NotificationType.CUSTOMER_CREATED -> {
                log.info("Sending welcome notification for customer {}", event.getCustomerId());
                welcomeUseCase.execute(event.getEmail(), event.getPhone(), event.getName());
            }

            case NotificationType.CUSTOMER_UPDATED -> {
                    log.info("Sending update notification for customer {}", event.getCustomerId());
                    updatedUseCase.execute(event.getEmail(), "7722102929", event.getName());
            }

            case NotificationType.CUSTOMER_DELETED -> {
                    log.info("Sending delete notification for customer {}", event.getCustomerId());
                    deletedUseCase.execute(event.getEmail(), event.getPhone(), event.getName());
            }

            default ->
                    log.warn("Unknown event type: {}", event.getEventType());
        }
    }

}
