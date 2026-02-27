package com.ordersystem.ms_notifications.infrastructure.sms.client;

public interface SmsClient {
    void send(String phone, String message);
}
