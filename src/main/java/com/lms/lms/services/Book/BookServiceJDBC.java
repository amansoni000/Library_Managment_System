package com.lms.lms.services.Book;

import com.lms.lms.dao.BookService;
import com.lms.lms.entiry.Books;
import com.lms.lms.mappers.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookServiceJDBC implements BookService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<Books> getAllBooks() {
        String query = "SELECT * FROM books";
        RowMapper<Books> BookList = new BookMapper();
        return jdbcTemplate.query(query, BookList);
    }

    @Override
    public int addBook(Books books) {
        String query = "INSERT INTO books(book_id, cur, actual, title, author) VALUES (:book_id, :cur, :actual, :title, :author)";
        MapSqlParameterSource newBook = new MapSqlParameterSource();
        newBook.addValue("book_id", books.getBook_id());
        newBook.addValue("cur", books.getCurrCount());
        newBook.addValue("actual", books.getActualCount());
        newBook.addValue("title", books.getTitle());
        newBook.addValue("author", books.getAuthor());
//        return jdbcTemplate.queryForObject(query, book, books.getBook_id(), books.getCurrCount(), books.getActualCount(), books.getTitle(), books.getAuthor());
        return namedParameterJdbcTemplate.update(query, newBook);
    }

    @Override
    public int updateBook(Books book) {
        String query = "UPDATE books SET cur = ?, actual = ?, title = ?, author = ? WHERE book_id = ?";
        return jdbcTemplate.update(query, book.getCurrCount(), book.getActualCount(), book.getTitle(), book.getAuthor(), book.getBook_id());
    }

    @Override
    public void deleteBook(int bookId) {
        String query = "DELETE FROM books WHERE book_id = ?";
        jdbcTemplate.update(query, bookId);
    }

    @Override
    public Books getBook(int bookId) {
        String query = " SELECT * FROM books WHERE book_id = ?";
        RowMapper<Books> book = new BookMapper();
        return jdbcTemplate.queryForObject(query, book, bookId);
    }
}
