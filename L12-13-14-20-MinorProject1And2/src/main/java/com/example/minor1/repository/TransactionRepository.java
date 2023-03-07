package com.example.minor1.repository;

import com.example.minor1.model.Book;
import com.example.minor1.model.Student;
import com.example.minor1.model.Transaction;
import com.example.minor1.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
    Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(
            Book book, Student student, TransactionType transactionType);
}
