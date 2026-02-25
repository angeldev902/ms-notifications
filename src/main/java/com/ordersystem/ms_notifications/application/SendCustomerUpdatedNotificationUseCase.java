package com.ordersystem.ms_notifications.application;

import com.ordersystem.ms_notifications.domain.model.Notification;
import com.ordersystem.ms_notifications.domain.port.NotificationSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SendCustomerUpdatedNotificationUseCase {
    private final NotificationSender notificationSender;

    public SendCustomerUpdatedNotificationUseCase(NotificationSender notificationSender) {
        this.notificationSender = notificationSender;
    }

    public void execute(String email, String name) {

        Notification notification = new Notification(
                email,
                "Updated account ",
                "updated-email",
                Map.of("name", name)
        );

        notificationSender.send(notification);
    }
}
