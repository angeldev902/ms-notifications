package com.ordersystem.ms_notifications.application.usecase;

import com.ordersystem.ms_notifications.domain.model.NotificationCommand;
import com.ordersystem.ms_notifications.domain.model.NotificationType;
import com.ordersystem.ms_notifications.domain.port.output.NotificationSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SendCustomerUpdatedNotificationUseCase {
    private final NotificationSender notificationSender;

    public SendCustomerUpdatedNotificationUseCase(NotificationSender notificationSender) {
        this.notificationSender = notificationSender;
    }

    public void execute(String email, String phone, String name) {

        NotificationCommand command =
                new NotificationCommand(
                        email,
                        phone,
                        Map.of("name", name),
                        NotificationType.CUSTOMER_UPDATED
                );

        notificationSender.notify(command);
    }
}
