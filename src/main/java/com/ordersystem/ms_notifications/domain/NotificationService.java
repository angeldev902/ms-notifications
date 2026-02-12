package com.ordersystem.ms_notifications.domain;

public interface NotificationService {
    void send(String to, String subject, String body);
}
