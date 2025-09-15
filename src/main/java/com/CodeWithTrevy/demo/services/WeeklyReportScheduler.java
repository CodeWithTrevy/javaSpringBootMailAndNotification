package com.CodeWithTrevy.demo.services;

import com.CodeWithTrevy.demo.dao.PostsRepository;
import com.CodeWithTrevy.demo.dao.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WeeklyReportScheduler {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    @Autowired
    public WeeklyReportScheduler(EmailService emailService,
                                 UserRepository userRepository,
                                 PostsRepository postsRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
    }

    @Scheduled(cron = "0 49 15 * * MON")
    public void sendWeeklyReport() {
        String adminEmail = "travorrwothmio@gmail.com";
        String reportContent = generateWeeklyReportContent();

        try {
            emailService.sendWeeklyReportEmail(adminEmail, reportContent);
            System.out.println("Weekly report email sent to: " + adminEmail);
        } catch (MessagingException e) {
            System.err.println("Failed to send weekly report email: " + e.getMessage());
        }
    }

    private String generateWeeklyReportContent() {
        LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(4);

        long postsCount = postsRepository.countPostsAfter(threeDaysAgo);
        long usersCount = userRepository.countUsersAfter(threeDaysAgo);





        return "This is your weekly summary report.\n"
                + "Total new users this week: " + usersCount + "\n"
                + "Total posts published: " + postsCount + "\n";
    }
}
