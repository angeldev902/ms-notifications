package com.ordersystem.ms_notifications.infrastructure;

import com.ordersystem.ms_notifications.domain.model.NotificationCommand;
import com.ordersystem.ms_notifications.domain.port.NotificationChannel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationOrchestrator {
    private final List<NotificationChannel> channels;

    public NotificationOrchestrator(List<NotificationChannel> channels) {
        this.channels = channels;
    }

    public void notify(NotificationCommand command) {
        channels.forEach(channel -> channel.send(command));
    }
}
