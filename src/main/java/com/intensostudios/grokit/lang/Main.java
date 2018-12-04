package com.intensostudios.grokit.lang;

import com.intensostudios.grokit.lang.controller.FlashcardController;
import com.intensostudios.grokit.lang.spring.SpringConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = {FlashcardController.class, SpringConfig.class})
@SpringBootApplication
public class Main {


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
