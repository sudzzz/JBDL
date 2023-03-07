package org.example;

import org.example.utils.BadPersonRequestException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ControllerAdvice
public class HandlerConfig {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex){
        // logic
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        List<String> errors = fieldErrors.stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity handleMethodIndexOutOfBoundEx(
            IndexOutOfBoundsException ex){
        // logic
        String message = ex.getMessage();

        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(BadPersonRequestException.class)
    public ResponseEntity handleBadPersonRequest(BadPersonRequestException ex){
        String message = ex.getMessage();

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
