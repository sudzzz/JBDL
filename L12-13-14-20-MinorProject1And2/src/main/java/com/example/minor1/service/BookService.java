package com.example.minor1.service;

import com.example.minor1.exception.BookNotFoundException;
import com.example.minor1.model.Book;
import com.example.minor1.request.BookCreateRequest;
import com.example.minor1.request.BookFilterTypeEnum;

import java.util.List;

public interface BookService {
    Book createOrUpdate(BookCreateRequest bookCreateRequest);
    List<Book> findBookByFilter(BookFilterTypeEnum bookFilterTypeEnum,String value) throws BookNotFoundException;
    Book createOrUpdate(Book book);
}
