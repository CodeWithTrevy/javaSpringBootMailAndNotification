package com.CodeWithTrevy.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class BirthDayCardsService {
    private final JavaMailSender mailSender;
    @Autowired

    public BirthDayCardsService(JavaMailSender mailSender){
        this.mailSender =mailSender;

    }
    public void SendBirthdayEmail(String to, String name){
        SimpleMailMessage message= new SimpleMailMessage();

        message.setTo(to);
        message.setFrom("travorrwothmio@gmail.com");
        message.setSubject("Happy  Birthday," + name);
        message.setText("Dear " + name + ",\n\nWishing you a very Happy Birthday! \n\nBest regards,\nYour Company");

        mailSender.send(message);
    }





}
