package com.ordersystem.ms_notifications.infrastructure.notification.sms.client;

public interface SmsClient {
    void send(String phone, String message);
}
