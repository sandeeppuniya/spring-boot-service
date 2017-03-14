package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@EnableAutoConfiguration
@ComponentScan
public class EmailServiceImpl implements EmailService {

    private static final String SEND_EMAIL_TO = "abc@gmail.com";
    private static final String SEND_EMAIL_FROM = "def@gmail.com";

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String emailText) {
        SimpleMailMessage simpleMailMessage = createEmailMessage(emailText);
        javaMailSender.send(simpleMailMessage);
    }

    /**
     * Creates the email message.
     *
     * @param emailText Text content sent in the email body.
     * @return simpleMailMessage Constructed email message.
     */
    private SimpleMailMessage createEmailMessage(String emailText) throws MailException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(SEND_EMAIL_TO);
        simpleMailMessage.setFrom(SEND_EMAIL_FROM);
        simpleMailMessage.setText(emailText);
        return simpleMailMessage;
    }
}
