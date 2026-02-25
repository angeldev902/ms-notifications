package com.ordersystem.ms_notifications.domain.model;

public class Notification {
    private final String recipient;
    private final String subject;
    private final String template;
    private final Object model;

    public Notification(String recipient, String subject, String template, Object model) {
        this.recipient = recipient;
        this.subject = subject;
        this.template = template;
        this.model = model;
    }

    public String getRecipient() { return recipient; }
    public String getSubject() { return subject; }
    public String getTemplate() { return template; }
    public Object getModel() { return model; }
}
