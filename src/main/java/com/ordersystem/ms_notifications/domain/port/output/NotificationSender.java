package com.ordersystem.ms_notifications.domain.port.output;

import com.ordersystem.ms_notifications.domain.model.NotificationCommand;

public interface NotificationSender {
    void notify(NotificationCommand command);
}
