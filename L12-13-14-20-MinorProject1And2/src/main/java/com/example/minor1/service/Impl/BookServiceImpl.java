package com.example.minor1.service.Impl;

import com.example.minor1.exception.BookNotFoundException;
import com.example.minor1.model.Author;
import com.example.minor1.model.Book;
import com.example.minor1.model.Genre;
import com.example.minor1.repository.AuthorRepository;
import com.example.minor1.repository.BookRepository;
import com.example.minor1.request.BookCreateRequest;
import com.example.minor1.request.BookFilterTypeEnum;
import com.example.minor1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Book createOrUpdate(BookCreateRequest bookCreateRequest) {
        Book book = bookCreateRequest.to();
        return createOrUpdate(book);
    }
    @Override
    public Book createOrUpdate(Book book) {
        Author author = book.getAuthor();
        /**
         * Find if author with given email exists in db or not?
         * If exists, then don't save, else save in the db.
         */
        Author authorSavedFromDB = authorRepository.findByEmail(author.getEmail());
        if(Objects.isNull(authorSavedFromDB)){
            authorSavedFromDB = authorRepository.save(author);
        }
        book.setAuthor(authorSavedFromDB);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findBookByFilter(BookFilterTypeEnum bookFilterTypeEnum, String value) throws BookNotFoundException {
        List<Book> bookList = new ArrayList<>();
        switch (bookFilterTypeEnum){
            case NAME :
                bookList = bookRepository.findByName(value);
                break;

            case AUTHOR_NAME :
                bookList = bookRepository.findByAuthor_Name(value);
                break;

            case GENRE :
                bookList = bookRepository.findByGenre(Genre.valueOf(value));
                break;
            case BOOK_ID:
                bookList = bookRepository.findAllById(Collections.singletonList(Integer.parseInt(value)));
                break;

            default :
                throw new BookNotFoundException("No book with given filter is present in library");

        }
        if(bookList.isEmpty()) throw new BookNotFoundException("No book with given filter is present in library");

        return bookList;
    }
}
