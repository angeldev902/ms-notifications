package com.ordersystem.ms_notifications.handler;

import com.ordersystem.ms_notifications.domain.CustomerEvent;
import com.ordersystem.ms_notifications.domain.NotificationService;
import com.ordersystem.ms_notifications.service.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.ordersystem.ms_notifications.application.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationEventHandler {

    private final SendWelcomeNotificationUseCase welcomeUseCase;
    private final SendCustomerUpdatedNotificationUseCase updatedUseCase;
    private final SendCustomerDeletedNotificationUseCase deletedUseCase;

    public void handle(CustomerEvent event) {

        switch (event.getEventType()) {

            case "CUSTOMER_CREATED" -> {
                log.info("Sending welcome notification for customer {}", event.getCustomerId());
                welcomeUseCase.execute(event.getEmail(), event.getName());
            }

            case "CUSTOMER_UPDATED" -> {
                    log.info("Sending update notification for customer {}", event.getCustomerId());
                    updatedUseCase.execute(event.getEmail(), event.getName());
            }

            case "CUSTOMER_DELETED" -> {
                    log.info("Sending delete notification for customer {}", event.getCustomerId());
                    deletedUseCase.execute(event.getEmail(), event.getName());
            }

            default ->
                    log.warn("Unknown event type: {}", event.getEventType());
        }
    }

}
