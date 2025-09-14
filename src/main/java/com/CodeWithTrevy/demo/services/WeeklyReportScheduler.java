package com.CodeWithTrevy.demo.scheduler;

import com.CodeWithTrevy.demo.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeeklyReportScheduler {

    private final EmailService emailService;

    @Autowired
    public WeeklyReportScheduler(EmailService emailService) {
        this.emailService = emailService;
    }

    // This cron expression means: "At 00:00 (midnight) on Saturday"
    // For testing, you might want to use a more frequent cron like "0 * * * * *" (every minute)
    @Scheduled(cron = "0 0 * * SAT") // Adjust this cron expression as needed
    public void sendWeeklyReport() {
        String adminEmail = "travorrwothmio@gmail.com";
        String reportContent = generateWeeklyReportContent(); // This method needs to be implemented

        try {
            emailService.sendWeeklyReportEmail(adminEmail, reportContent);
            System.out.println("Weekly report email sent to: " + adminEmail);
        } catch (MessagingException e) {
            System.err.println("Failed to send weekly report email: " + e.getMessage());
            // Log the exception properly
        }
    }

    private String generateWeeklyReportContent() {
        // Here you would fetch data from your database,
        // analyze it, and format it into a report string.
        // For demonstration, let's just return a placeholder.
        return "This is your weekly summary report. "
                + "Total new users this week: 10. "
                + "Total posts published: 25. "
                + "Most viewed post: 'Spring Boot Basics'.";
    }
}