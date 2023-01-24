package com.lms.lms.mappers;

import com.lms.lms.entiry.Books;
import com.lms.lms.entiry.Issue_Return;
import com.lms.lms.entiry.Users;
import org.springframework.jdbc.core.RowMapper;

import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class IssueReturnMapper implements RowMapper<Issue_Return> {
    @Override
    public Issue_Return mapRow(ResultSet rs, int rowNum) throws SQLException {
        Issue_Return new_issue_return = new Issue_Return();
        new_issue_return.setUser_id(rs.getInt("user_id"));
        new_issue_return.setBookId(rs.getInt("book_id"));
        new_issue_return.setIssueDate(rs.getDate("issue_date"));
        new_issue_return.setReturnDate(rs.getDate("return_date"));
        new_issue_return.setExpected_return_date(rs.getDate("expected_return_date"));
        new_issue_return.setStatus(rs.getString("status"));
        new_issue_return.setFine(rs.getInt("fine"));
        return new_issue_return;
    }
}
