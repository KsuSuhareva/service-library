package ru.itq.library_service.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.itq.library_service.config.LibraryProperties;

@Component
@RequiredArgsConstructor
public class EmailSender implements NotificationSender {
    private final LibraryProperties properties;
    private final JavaMailSender mailSender;

    @Override
    public void send(Message message) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(message.getRecipientContactArray());
        simpleMessage.setFrom(properties.getMailFrom());
        simpleMessage.setSubject(message.getTitle());
        simpleMessage.setText(message.getText());
        mailSender.send(simpleMessage);
    }
}
