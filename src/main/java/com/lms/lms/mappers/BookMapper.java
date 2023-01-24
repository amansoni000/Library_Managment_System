package com.lms.lms.mappers;

import com.lms.lms.entiry.Books;
import com.lms.lms.entiry.Users;
import org.springframework.jdbc.core.RowMapper;

import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Books> {
    @Override
    public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
        Books book = new Books();
        book.setBook_id(rs.getInt("book_id"));
        book.setAuthor(rs.getString("author"));
        book.setTitle(rs.getString("title"));
        book.setCurrCount(rs.getInt("cur"));
        book.setActualCount(rs.getInt("actual"));
        return book;
    }

}