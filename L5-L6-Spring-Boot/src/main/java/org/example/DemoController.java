package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Dispatcher servlet searches for @Controller mapping classes.
@RequestMapping("/v1")
public class DemoController {

 //   @Autowired  This annotation picks the reference of class from the IOC container
    Demo demo;

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    public DemoController(Demo demo){
        this.demo = demo;
        logger.info("Creating Object... {}",this);
    }

    public DemoController(@Value("${server.port}") int a){
        logger.info("a is... {}",a);
    }

    @GetMapping("/sample")
    public Demo getDemo(){
        logger.info("Demo object in sample get API v1.. {}",demo);
        return demo;
    }

    @PostMapping("/post_sample")
    public Demo postDemo(){
        logger.info("Demo object in sample post API.. {}",demo);
        return demo;
    }
}

//DemoController@582a764a --> Created by me in main class
//DemoController@c808207  --> Created by Spring
