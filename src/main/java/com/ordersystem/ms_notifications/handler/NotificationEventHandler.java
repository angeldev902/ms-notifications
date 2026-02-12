package com.ordersystem.ms_notifications.handler;

import com.ordersystem.ms_notifications.domain.CustomerEvent;
import com.ordersystem.ms_notifications.domain.NotificationService;
import com.ordersystem.ms_notifications.service.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationEventHandler {

    private final NotificationService notificationService;

    public void handle(CustomerEvent event) {

        switch (event.getEventType()) {

            case "CUSTOMER_CREATED" -> {
                log.info("Sending welcome notification for customer {}", event.getCustomerId());
                notificationService.send(
                        event.getEmail(),
                        "Welcome to Order System 🎉",
                        "Hello " + event.getName() + ",\n\n" +
                                "Your account has been successfully created.\n\n" +
                                "Thank you for joining us."
                );
            }

            case "CUSTOMER_UPDATED" -> {
                    log.info("Sending update notification for customer {}", event.getCustomerId());
                    notificationService.send(
                        event.getEmail(),
                        "Updated account 🎉",
                        "Hello " + event.getName() + ",\n\nYour account has been successfully updated."
                    );
            }

            case "CUSTOMER_DELETED" -> {
                    log.info("Sending delete notification for customer {}", event.getCustomerId());
                notificationService.send(
                        event.getEmail(),
                        "Deleted account 🎉",
                        "Hello " + event.getName() + ",\n\nYour account has been successfully deleted."
                );
            }

            default ->
                    log.warn("Unknown event type: {}", event.getEventType());
        }
    }

}
