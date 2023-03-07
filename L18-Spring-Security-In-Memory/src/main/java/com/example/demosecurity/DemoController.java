package com.example.demosecurity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String greet(){
        return "Hello World!!";
    }

    @GetMapping("/demo2")
    public String greet2(){
        return "Hello World2!!";
    }

    @GetMapping("/testcode")
    public String testCode(){
        return "Testing the code....";
    }

    @GetMapping("/developcode")
    public String developCode(){
        return "Developing the code....";
    }

    @GetMapping("/accessserver")
    public String accessServer(){
        return "Accessing the server....";
    }

    @GetMapping("/home")
    public String home(){
        return "Welcome to the home page....";
    }

    @GetMapping("/home/all")
    public String homeAll(){
        return "Welcome to the home page all....";
    }


    // 03885c7d-7e88-4d6e-895c-63cc94bd5d32

    // 5608F241C6961F59FA4184D712ADF209 - session id of previously logged in user
}

/*
        Request comes to the BE
        1. JSESSIONID sent is of authenticated session / user
            BE work : verify the session id, return the response of the request resource
        2. JSESSIONID sent is of unauthenticated session / user
            BE work: verify the session id, redirect to /login API
            End user: pass the credentials
            FE: makes a call to /login API with unauthenticated JSESSIONID + payload (username and password)
            BE: verify on basis of username and pwd  and generate a new authenticated session and return the id of that
                as a set-cookie in the response header

 */