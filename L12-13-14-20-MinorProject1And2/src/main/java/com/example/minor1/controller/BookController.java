package com.example.minor1.controller;

import com.example.minor1.exception.BookNotFoundException;
import com.example.minor1.model.Book;
import com.example.minor1.request.BookCreateRequest;
import com.example.minor1.request.BookFilterTypeEnum;
import com.example.minor1.response.BookSearchResponse;
import com.example.minor1.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping("/book")
    public ResponseEntity<Book> createBookRequest(@Valid @RequestBody BookCreateRequest bookCreateRequest){
        return new ResponseEntity<>(bookService.createOrUpdate(bookCreateRequest), HttpStatus.CREATED);
    }

    //GET - filter functionality / search
    @GetMapping("/books/search")
    public List<BookSearchResponse> findBooks(
            @RequestParam("filter")BookFilterTypeEnum bookFilterTypeEnum,
            @RequestParam("value") String value) throws BookNotFoundException {
        return bookService.findBookByFilter(bookFilterTypeEnum,value).stream()
                .map(Book::to)
                .collect(Collectors.toList());
    }

}
