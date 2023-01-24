package com.lms.lms.Controller;

import com.lms.lms.entiry.Books;
import com.lms.lms.dao.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("home/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // Getting All books Details int the library
    @GetMapping
    public List<Books> getAllBooks(){
        return bookService.getAllBooks();
    }

    // Getting book detail by book id
    @GetMapping("{id}")
    public Books getBook(@PathVariable String id){
        return bookService.getBook(Integer.parseInt(id));
    }

    // Adding a book in library
    @PostMapping
    public int addBook(@RequestBody Books book){
        return bookService.addBook(book);
    }

    // updating the book details in the library
    @PutMapping
    public int updateBook(@RequestBody Books book){
        return bookService.updateBook(book);
    }

    // deleting the book from the library
    @DeleteMapping("{book_id}")
    public void deleteBook(@PathVariable int book_id){
        bookService.deleteBook(book_id);
    }

}
