package com.ordersystem.ms_notifications.domain.port;

import com.ordersystem.ms_notifications.domain.model.Notification;

public interface NotificationSender {
    void send(Notification notification);
}
