package com.example.minor1.service;

import com.example.minor1.exception.BookNotFoundException;
import com.example.minor1.exception.StudentNotFoundException;
import com.example.minor1.exception.TransactionServiceException;

public interface TransactionService {
    String issueTxn(int studentId,int bookId) throws StudentNotFoundException, BookNotFoundException, TransactionServiceException;

    String returnTxn(int studentId,int bookId) throws StudentNotFoundException, BookNotFoundException, TransactionServiceException;
}
