package com.example.minor1.service.Impl;

import com.example.minor1.exception.BookNotFoundException;
import com.example.minor1.exception.StudentNotFoundException;
import com.example.minor1.exception.TransactionServiceException;
import com.example.minor1.model.Book;
import com.example.minor1.model.Student;
import com.example.minor1.model.Transaction;
import com.example.minor1.model.TransactionType;
import com.example.minor1.repository.TransactionRepository;
import com.example.minor1.request.BookFilterTypeEnum;
import com.example.minor1.service.BookService;
import com.example.minor1.service.StudentService;
import com.example.minor1.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private StudentService studentService;
    @Autowired
    private BookService bookService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Value("${book.return.due_date}")
    int number_of_days;

    @Transactional // we want this function to work atomically so that there might not be any case where 2 students issue same book
    @Override
    public String issueTxn(int studentId, int bookId) throws StudentNotFoundException, BookNotFoundException, TransactionServiceException {
        /**
         * 1. Check if Student is a valid entity
         * 2. Check if Book is present and available
         * 3. Then create a transaction --> saving in the db
         * 4. Make the book unavailable.
         */
        Student student = studentService.findStudentById(studentId);
        if(Objects.isNull(student)){
            throw new StudentNotFoundException("Student with id "+studentId+" is not present in the library");
        }

        List<Book> books = bookService.findBookByFilter(BookFilterTypeEnum.BOOK_ID,String.valueOf(bookId));

        Book book = books.get(0);
        if(Objects.nonNull(book.getStudent())){
            throw new TransactionServiceException("Book is already assigned to someone.");
        }
        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .payment(book.getCost())
                .book(book)
                .student(student)
                .build();
        transactionRepository.save(transaction);
        book.setStudent(student);
        bookService.createOrUpdate(book);
        return transaction.getExternalTxnId();
    }

    @Override
    public String returnTxn(int studentId, int bookId) throws StudentNotFoundException, BookNotFoundException, TransactionServiceException {
        /**
         * 1. Check if student is a valid entity
         * 2. Check if book is issued to this particular student
         * 3. Calculate the fine
         * 4. Create a transaction --> saving in transaction table
         * 5. Make the book available
         */

        Student student = studentService.findStudentById(studentId);
        if(Objects.isNull(student)){
            throw new StudentNotFoundException("Student with id "+studentId+" is not present in the library");
        }

        List<Book> books = bookService.findBookByFilter(BookFilterTypeEnum.BOOK_ID,String.valueOf(bookId));
        Book book = books.get(0);

        if(Objects.isNull(book.getStudent()) || book.getStudent().getId()!=studentId){
            throw new TransactionServiceException("Book with id "+bookId+" was not issued to student with studentId "+studentId);
        }


        /**
         * Finding the original issue_txn
         * select * from transaction where book_id = ? and student_id = ? and transaction_type = 'ISSUE' order by transaction_date desc
         * we will always take the first row in consideration to the result of above query.
         */

        Transaction issueTransaction = transactionRepository.findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(
                book,student,TransactionType.ISSUE);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.RETURN)
                .externalTxnId(UUID.randomUUID().toString())
                .book(book)
                .student(student)
                .payment(calculateFine(issueTransaction))
                .build();

        transactionRepository.save(transaction);
        book.setStudent(null); //As the book is now available.
        bookService.createOrUpdate(book);
        return transaction.getExternalTxnId();
    }

    private double calculateFine( Transaction issueTransaction){
        Date issueDate = issueTransaction.getTransactionDate();
        Date currentDate = new Date();

        long issueTime = issueDate.getTime();
        long returnTime = currentDate.getTime();

        long diff = returnTime - issueTime;

        long daysPassed = TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
        double amount = 0.0;
        if(daysPassed>number_of_days){
            amount = daysPassed - number_of_days;
        }
        return amount;
    }
}
