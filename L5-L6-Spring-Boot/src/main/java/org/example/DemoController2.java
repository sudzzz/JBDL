package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v2")
public class DemoController2 {

    @Autowired
    Demo demo;

    @Autowired
    DemoController demoController;

    @Autowired
    DemoConfig demoConfig;

    @Autowired
    @Qualifier("getTemplate2")
    RestTemplate restTemplate2;

    @Autowired
    @Qualifier("getTemplate")
    RestTemplate restTemplate;
    private static Logger logger = LoggerFactory.getLogger(DemoController2.class);

    @GetMapping("/sample")
    public Demo getDemo(){
        logger.info("Demo object in sample get API v2 .. {}",demo);
        //To use bean of demo created in DemoController we have to Autowired DemoController in DemoController2. As the Scope is
        //prototype and both the objects created are of same name, demo, we can not use @Qualifier annotation to use bean of demo
        //created in DemoController2
        logger.info("demoController - {}, demo of dc1 - {}, demo of dc2 - {}",demoController,demoController.demo,demo);
        return demo;
    }

    @GetMapping("/getTemplate2")
    public String getTemplate2(){
        logger.info("restTemplate --- {}, restTemplate2 --- {}",restTemplate,restTemplate2);
        return "Sample String!!";
    }

    @GetMapping("/getTemplate")
    public String getTemplate(){
        RestTemplate restTemplate = demoConfig.getTemplate();
        logger.info("restTemplate --- {}",restTemplate);
        return restTemplate.toString();
    }
}

//Demo object in sample get API v1.. org.example.Demo@2dc8f5f3
//Demo object in sample get API v2 .. org.example.Demo@2dc8f5f3

//Both are same --> it means instance is shared and bean scope is singleton.

//If scope is prototype
//Demo object in sample get API v1.. org.example.Demo@270b6b5e
//Demo object in sample get API v2 .. org.example.Demo@1706a5c9

//Above both are different