package com.example.demojpa.configs;

import com.example.demojpa.exceptions.PersonNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerConfig {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity handlePersonNotFoundEx(PersonNotFoundException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
