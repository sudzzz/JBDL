package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DemoConfig {

    private static final Logger logger = LoggerFactory.getLogger(DemoConfig.class);
    @Bean
    public RestTemplate getTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        logger.info("Inside getTemplate() --- {}",restTemplate);
        return restTemplate;
    }

    @Bean
    public RestTemplate getTemplate2(){
        RestTemplate restTemplate = new RestTemplate();
        logger.info("Inside getTemplate() --- {}",restTemplate);
        return restTemplate;
    }
}
