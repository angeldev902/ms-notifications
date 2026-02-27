package com.ordersystem.ms_notifications.infrastructure.sms;

import com.ordersystem.ms_notifications.domain.model.NotificationCommand;
import com.ordersystem.ms_notifications.domain.port.NotificationChannel;
import com.ordersystem.ms_notifications.infrastructure.sms.client.SmsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationChannel implements NotificationChannel {
    private final SmsClient smsClient;
    private static final Logger log = LoggerFactory.getLogger(SmsNotificationChannel.class);

    public SmsNotificationChannel(SmsClient smsClient) {
        this.smsClient = smsClient;
    }

    @Override
    public void send(NotificationCommand command) {

        if (command.getPhone() == null || command.getPhone().isBlank()) {
            return;
        }

        String name = (String) command.getVariables().get("name");

        String message = switch (command.getType()) {
            case CUSTOMER_CREATED ->
                    "Hi " + name + ", your account was created!";
            case CUSTOMER_UPDATED ->
                    "Hi " + name + ", your account was updated!";
            case CUSTOMER_DELETED ->
                    "Hi " + name + ", your account was deleted!";
        };

        log.info("SMS message={}", message);
        smsClient.send(command.getPhone(), message);

    }
}
