package com.example.java_gobang2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavaGobang2Application {
    public static ConfigurableApplicationContext context;
    public static void main(String[] args) {
        context = SpringApplication.run(JavaGobang2Application.class, args);
    }

}
