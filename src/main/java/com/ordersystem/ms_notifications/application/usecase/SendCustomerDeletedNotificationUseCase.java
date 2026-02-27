package com.ordersystem.ms_notifications.application;

import com.ordersystem.ms_notifications.domain.model.NotificationCommand;
import com.ordersystem.ms_notifications.domain.model.NotificationType;
import com.ordersystem.ms_notifications.infrastructure.NotificationOrchestrator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SendCustomerDeletedNotificationUseCase {
    private final NotificationOrchestrator orchestrator;

    public SendCustomerDeletedNotificationUseCase(NotificationOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    public void execute(String email, String phone, String name) {

        NotificationCommand command =
                new NotificationCommand(
                        email,
                        phone,
                        Map.of("name", name),
                        NotificationType.CUSTOMER_DELETED
                );

        orchestrator.notify(command);
    }
}
