package com.ordersystem.ms_notifications.handler;

import com.ordersystem.ms_notifications.domain.CustomerEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationEventHandler {

    public void handle(CustomerEvent event) {

        switch (event.getEventType()) {

            case "CUSTOMER_CREATED" ->
                    log.info("Sending welcome notification for customer {}", event.getCustomerId());

            case "CUSTOMER_UPDATED" ->
                    log.info("Sending update notification for customer {}", event.getCustomerId());

            case "CUSTOMER_DELETED" ->
                    log.info("Sending delete notification for customer {}", event.getCustomerId());

            default ->
                    log.warn("Unknown event type: {}", event.getEventType());
        }
    }

}
