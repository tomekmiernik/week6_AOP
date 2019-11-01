package com.example.week6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:credentials.properties")
@SpringBootApplication
public class Week6Application {

    public static void main(String[] args) {
        SpringApplication.run(Week6Application.class, args);
    }

}
