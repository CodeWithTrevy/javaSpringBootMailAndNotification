package com.CodeWithTrevy.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ExternalApiService {
    private final RestClient restClient;
    private final String apiKey;
    @Autowired



    public  ExternalApiService(RestClient.Builder restClientBuilder, @Value("${weather.api.key}")  String apiKey){
        this.apiKey = apiKey;
        this.restClient = restClientBuilder
                //.baseUrl("https://restcountries.com/v3.1/independent?status=true")
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .build();


    }

    public String fetchDataFromExternalApi(String city){
            return restClient.get()
//                    .uri("independent?status=true")

                    .uri(uriBuilder -> uriBuilder
                            .path("weather")
                            .queryParam("q", city)
                            .queryParam("appid", apiKey)
                            .build())
                    .retrieve()
                    .body(String.class);
    }
}
