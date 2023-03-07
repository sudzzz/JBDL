package com.example.minor1.controller;

import com.example.minor1.exception.BookNotFoundException;
import com.example.minor1.exception.StudentNotFoundException;
import com.example.minor1.exception.TransactionServiceException;
import com.example.minor1.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("transaction/issue")
    public ResponseEntity<String> issueTransaction(@RequestParam("studentId") int studentId,
                                           @RequestParam("bookId") int bookId) throws StudentNotFoundException, BookNotFoundException, TransactionServiceException {
        return new ResponseEntity<>(transactionService.issueTxn(studentId,bookId), HttpStatus.CREATED);
    }

    @PostMapping("transaction/return")
    public ResponseEntity<String> returnTransaction(@RequestParam("studentId") int studentId,
                                                   @RequestParam("bookId") int bookId) throws StudentNotFoundException, BookNotFoundException, TransactionServiceException {
        return new ResponseEntity<>(transactionService.returnTxn(studentId,bookId), HttpStatus.CREATED);
    }

}
