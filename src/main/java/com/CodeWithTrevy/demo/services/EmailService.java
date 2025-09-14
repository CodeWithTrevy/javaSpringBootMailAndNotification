package com.CodeWithTrevy.demo.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendWelcomeEmail(String toEmail, String username)
            throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(toEmail);
        helper.setSubject("Welcome to Blog Application!");
        helper.setText(
                "<h2>Hi " + username + ",</h2>"
                        + "<p>Welcome to our Blog Application! We're glad to have you.</p>"
                        + "<p>Cheers,<br/>Team</p>",
                true
        );

        helper.setFrom("travorrwothmio@gmail.com");

        FileSystemResource file = new FileSystemResource(new File("/home/trevor/Downloads/demo/src/main/resources/static/welcome.png"));
        helper.addAttachment(file.getFilename(), file);

        mailSender.send(mimeMessage);
    }

    public void sendWeeklyReportEmail(String toEmail, String reportContent) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(toEmail);
        helper.setSubject("Weekly Blog Application Report");
        helper.setText(
                "<h2>Weekly Report,</h2>"
                        + "<p>" + reportContent + "</p>"
                        + "<p>Cheers,<br/>Team</p>",
                true
        );
        helper.setFrom("travorrwothmio@gmail.com");
        mailSender.send(mimeMessage);
    }
}