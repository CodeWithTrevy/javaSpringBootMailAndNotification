package com.CodeWithTrevy.demo;

import com.CodeWithTrevy.demo.dao.UserRepository;
import com.CodeWithTrevy.demo.model.Users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;


@SpringBootApplication
@EnableScheduling

public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

    }


}
