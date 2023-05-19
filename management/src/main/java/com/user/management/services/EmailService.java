package com.user.management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Carine Iradukunda
 */
@Service
@Component
public class EmailService  implements EmailServiceOp{
//    @Autowired
//    private JavaMailSender mailSender;


    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

       // mailSender.send(message);
    }
}
