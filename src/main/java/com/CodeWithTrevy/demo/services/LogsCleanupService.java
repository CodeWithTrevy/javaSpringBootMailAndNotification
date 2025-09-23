package com.CodeWithTrevy.demo.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class LogsCleanupService {

    //private static final String LOG_DIRECTORY = "/home/trevor/Downloads/demo/logs";
    //private static final String LOG_DIRECTORY = System.getProperty("user.dir") + "/logs";

    private static final String LOG_DIRECTORY = "/home/trevor/Downloads/demo/logs";

    //private static final long DAYS_TO_KEEP_LOGS = 5; // in testing in days

    private static final long Time_To_KEEP_LOGS = 2;  //for testing bit in days


   // @Scheduled(cron = "0 0 2 * * ?")

    @Scheduled(cron = "0/10 * * * * ?")
    public void cleanOldLogs() {
        File logDir = new File(LOG_DIRECTORY);

        if (logDir.exists() && logDir.isDirectory()) {
            File[] files = logDir.listFiles();
            if (files != null) {
                Instant cutoffDate = Instant.now().minus(Time_To_KEEP_LOGS, ChronoUnit.MINUTES);

                for (File file : files) {
                    try {
                        Instant fileTime = Files.getLastModifiedTime(Paths.get(file.getAbsolutePath())).toInstant();
                        if (fileTime.isBefore(cutoffDate)) {
                            if (file.delete()) {
                                System.out.println("Deleted old log file: " + file.getName());
                            } else {
                                System.out.println("Failed to delete log file: " + file.getName());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error processing file " + file.getName() + " -> " + e.getMessage());
                    }
                }
            }
        } else {
            System.out.println("Log directory not found: " + LOG_DIRECTORY);
        }
    }
}
