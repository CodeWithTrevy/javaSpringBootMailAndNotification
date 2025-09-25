package com.CodeWithTrevy.demo.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class WeatherSyncTask {
    private static final Logger logger = LoggerFactory.getLogger(WeatherSyncTask.class);

    private  ExternalApiService externalApiService;

    @Autowired
    public WeatherSyncTask(ExternalApiService externalApiService){
        this.externalApiService = externalApiService;
    }

    @Scheduled(fixedRate = 300000)
    public void synWeatherData(){
        logger.info("starting weather data syn......");
        try {
            String weatherData= externalApiService.fetchDataFromExternalApi("Kampala");
            logger.info("Successfully fetched weather data: {}",weatherData);

        } catch (Exception e) {

            logger.error("Error occurred during weather Data sync", e.getMessage());


        }
        logger.info("finished weather data  sync  ");
    }


}
