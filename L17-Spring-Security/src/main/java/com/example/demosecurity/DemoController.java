package com.example.demosecurity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String greet(){
        return "Hello World";
    }

}
