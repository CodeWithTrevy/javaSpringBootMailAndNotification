package com.CodeWithTrevy.demo.services;


import com.CodeWithTrevy.demo.repository.BirthDayCardsRepository;
import com.CodeWithTrevy.demo.model.BirthDayCards;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class BirthdayScheduler {
    private final BirthDayCardsRepository birthDayCardsRepository;
    private final BirthDayCardsService birthDayCardsService;
    private static final Logger logger = LoggerFactory.getLogger(BirthdayScheduler.class);

    @Autowired

    public BirthdayScheduler(BirthDayCardsRepository birthDayCardsRepository,BirthDayCardsService birthDayCardsService){

        this.birthDayCardsRepository= birthDayCardsRepository;
        this.birthDayCardsService = birthDayCardsService;

    }

    @Scheduled(cron = "0 39 16 * * ?")
    public void sendBirthdayGreetings(){
        LocalDate today = LocalDate.now();
        List<BirthDayCards> birthDayCards = birthDayCardsRepository.findCardsWithBirthdayToday(today.getMonthValue(), today.getDayOfMonth());
        for(BirthDayCards birthDayCard : birthDayCards) {
            try {
                birthDayCardsService.SendBirthdayEmail(birthDayCard.getEmail(), birthDayCard.getFirstName());
                logger.info("sent birthday email to {}", birthDayCard.getEmail());


            } catch (Exception e) {
                logger.error("Failed to send email to {}", birthDayCard.getEmail(), e);


            }

            logger.info("Birthday email scheduler finished for today {}", today);



        }





    }
}
