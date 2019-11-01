package com.example.week6.aspect;

import com.example.week6.service.EmailService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MovieAspect {

    private EmailService emailService;

    @Autowired
    public MovieAspect(EmailService emailService) {
        this.emailService = emailService;
    }

   @After(value = "@annotation(SendEmailAfterAddNewMovie)")
    private void methodRunAfterAddMovie(){
        emailService.sendEmail("your@email.com",
                "Add new movie",
                "In your api collection movies, somebody add new movie item.");
    }
}
