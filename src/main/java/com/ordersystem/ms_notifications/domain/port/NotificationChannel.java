package com.ordersystem.ms_notifications.domain.port;

import com.ordersystem.ms_notifications.domain.model.NotificationCommand;

public interface NotificationChannel {
    void send(NotificationCommand notification);
}
