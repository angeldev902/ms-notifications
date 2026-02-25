package com.ordersystem.ms_notifications.application;

import com.ordersystem.ms_notifications.domain.model.Notification;
import com.ordersystem.ms_notifications.domain.port.NotificationSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SendWelcomeNotificationUseCase {
    private final NotificationSender notificationSender;

    public SendWelcomeNotificationUseCase(NotificationSender notificationSender) {
        this.notificationSender = notificationSender;
    }

    public void execute(String email, String name) {

        Notification notification = new Notification(
                email,
                "Welcome to Order System",
                "welcome-email",
                Map.of("name", name)
        );

        notificationSender.send(notification);
    }

}
