package com.lms.lms.Controller;

import com.lms.lms.entiry.Books;
import com.lms.lms.dao.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("home/books")
    public List<Books> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("home/books/{id}")
    public Books getBook(@PathVariable String id){
        return bookService.getBook(Integer.parseInt(id));
    }

    @PostMapping("home/books")
    public int addBook(@RequestBody Books book){
        return bookService.addBook(book);
    }

    @PutMapping("home/books")
    public int updateBook(@RequestBody Books book){
        return bookService.updateBook(book);
    }

    @DeleteMapping("home/books/{book_id}")
    public void deleteBook(@PathVariable int book_id){
        bookService.deleteBook(book_id);
    }

}
