package com.example.minor1.config;

import com.example.minor1.exception.BookNotFoundException;
import com.example.minor1.exception.StudentNotFoundException;
import com.example.minor1.exception.TransactionServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.transaction.TransactionalException;

@ControllerAdvice
public class HandlerConfig {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity handleBookNotFound(BookNotFoundException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity handleStudentNotFound(StudentNotFoundException ex){
        return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionServiceException.class)
    public ResponseEntity handleTransactionException(TransactionServiceException ex){
        return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity handleUsernameException(UsernameNotFoundException ex){
        return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
