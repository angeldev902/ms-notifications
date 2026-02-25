package com.ordersystem.ms_notifications.infrastructure.email;

import com.ordersystem.ms_notifications.domain.model.Notification;
import com.ordersystem.ms_notifications.domain.port.NotificationSender;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Component
public class EmailNotificationSender implements NotificationSender {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailNotificationSender(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void send(Notification notification) {
        try {
            Context context = new Context();

            if (notification.getModel() instanceof Map<?, ?> variables) {
                variables.forEach((key, value) ->
                        context.setVariable(key.toString(), value));
            }

            String html = templateEngine.process(
                    notification.getTemplate(),
                    context
            );

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(notification.getRecipient());
            helper.setSubject(notification.getSubject());
            helper.setText(html, true);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Error sending email", e);
        }
    }

}
