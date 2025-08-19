package com.example.dream_stadium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DreamStadiumApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamStadiumApplication.class, args);
    }

}
