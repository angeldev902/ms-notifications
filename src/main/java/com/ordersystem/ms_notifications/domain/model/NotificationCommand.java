package com.ordersystem.ms_notifications.domain.model;

import java.util.Map;

public class NotificationCommand {
    private final String email;
    private final String phone; // puede ser null
    private final Map<String, Object> variables;
    private final NotificationType type;

    public NotificationCommand(String email, String phone, Map<String, Object> variables, NotificationType type) {
        this.email = email;
        this.phone = phone;
        this.variables = variables;
        this.type = type;
    }

    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Map<String, Object> getVariables() { return variables; }
    public NotificationType getType() { return type; }
}
