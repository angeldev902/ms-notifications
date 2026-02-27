package com.ordersystem.ms_notifications.infrastructure.email;

import com.ordersystem.ms_notifications.domain.model.NotificationCommand;
import com.ordersystem.ms_notifications.domain.port.NotificationChannel;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Component
public class EmailNotificationSender implements NotificationChannel {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailNotificationSender(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void send(NotificationCommand command) {
        if (command.getEmail() == null || command.getEmail().isBlank()) {
            return;
        }
        try {
            Context context = new Context();

            command.getVariables().forEach(context::setVariable);

            String templateName = switch (command.getType()) {
                case CUSTOMER_CREATED -> "welcome-email";
                case CUSTOMER_UPDATED -> "updated-email";
                case CUSTOMER_DELETED -> "deleted-email";
            };
            String subject = switch (command.getType()) {
                case CUSTOMER_CREATED -> "Welcome to Order System";
                case CUSTOMER_UPDATED -> "Updated account";
                case CUSTOMER_DELETED -> "Delete account";
            };

            String html = templateEngine.process(templateName, context);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(command.getEmail());
            helper.setSubject(subject);
            helper.setText(html, true);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Error sending email", e);
        }
    }

}
