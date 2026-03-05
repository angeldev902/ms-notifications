package com.ordersystem.ms_notifications.infrastructure.notification;

import com.ordersystem.ms_notifications.domain.model.NotificationCommand;
import com.ordersystem.ms_notifications.domain.port.NotificationChannel;
import com.ordersystem.ms_notifications.domain.port.output.NotificationSender;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component
public class NotificationOrchestrator implements NotificationSender {
    private final List<NotificationChannel> channels;
    private static final Logger log = LoggerFactory.getLogger(NotificationOrchestrator.class);

    public NotificationOrchestrator(List<NotificationChannel> channels) {
        this.channels = channels;
    }

    @Override
    public void notify(NotificationCommand command) {

        for (NotificationChannel channel : channels) {
            try {
                channel.send(command);
            } catch (Exception e) {
                log.error("Channel {} failed", channel.getClass().getSimpleName(), e);
            }
        }
    }
}
