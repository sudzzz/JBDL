package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    private HashMap<Integer, Book> bookHashMap = new HashMap();

    /*
        GET - Retrieve some info
        POST - Save some info on the server
        DELETE - Delete something on the server
        PUT - Updates the already existing info on the server
     */

    // To fetch the bookId from the client and return
    // the details of that particular book
//    @RequestMapping(value = "/book", method = {RequestMethod.GET, RequestMethod.POST})
    @GetMapping("/book")
    public Book getBook(@RequestParam(value = "bid", required = false, defaultValue = "1") Integer bookId){
        logger.info("bID - {}", bookId);
        return bookHashMap.get(bookId);
    }

    @GetMapping("/book/{bookId}")
    public Book getBookById(@PathVariable("bookId") int bookId){
        logger.info("bID - {}", bookId);
        return bookHashMap.get(bookId);
    }

    @GetMapping("/book/all")
    public List<Book> getBooks(){
        return bookHashMap.values()
                .stream()
                .collect(Collectors.toList());
    }

    // No two API should be similar
    /*
        Same path + Same HTTP method
     */

    // Not a good to implement API
//    @PostMapping("/book")
//    public void insertBook(@RequestParam("bid") String bookId,
//                           @RequestParam("bname") String bookName,
//                           @RequestParam("bcost") Integer cost,
//                           @RequestParam("bauthor") String author){
//
//    }

    @PostMapping("/insert_book")
    public void insertBook(@RequestBody Book book, @RequestParam("sampleParam") String msg){
        logger.info("Book coming from the request - {}, msg - {}", book, msg);
        bookHashMap.put(book.getId(), book);
    }

    // FE and BE -->
    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book, @RequestParam("id") int bookId){
        bookHashMap.put(bookId, book);
        return bookHashMap.get(bookId);
    }

    @DeleteMapping("/book")
    public Book deleteBook(@RequestParam("id") int id){
        return bookHashMap.remove(id);
    }

    @GetMapping("/esps/{corporateId}/corporates/{espId}/companies/{companyId}")
    public String dummy2(){
//        logger.info("id - {}, param - {}", id, param);
//        return "Hello!";

        return "Hello!";
    }

    @GetMapping("/corporates/{corporateId}/esps/{espId}/companies/{companyId}")
    public String dummy(){
//        logger.info("id - {}, param - {}", id, param);
//        return "Hello!";

        return "Hello!";
    }

}

