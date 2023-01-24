package com.lms.lms.dao;
import java.util.List;
import com.lms.lms.entiry.Books;
import org.springframework.stereotype.Service;

public interface BookService {
    public List<Books> getAllBooks();

    public int addBook(Books book);

    public int updateBook(Books book);

    public void deleteBook(int bookId);

    public Books getBook(int bookId);
}
