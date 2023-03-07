package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSpringApplication {

    private static final Logger logger = LoggerFactory.getLogger(DemoSpringApplication.class);
    public static void main(String[] args) {

        SpringApplication.run(DemoSpringApplication.class,args);
        logger.info("My application has started");
    }
}