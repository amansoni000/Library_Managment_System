package com.lms.lms.services.issue_return;

import com.lms.lms.dao.Issue_ReturnService;
import com.lms.lms.entiry.Issue_Return;
import com.lms.lms.mappers.IssueReturnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;

@Service
public class Issue_ReturnJDBC implements Issue_ReturnService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<Issue_Return> getAllIssuedBooks() {
        String query = "SELECT * FROM issue_return";
        RowMapper<Issue_Return> IssueReturnList = new IssueReturnMapper();
        return jdbcTemplate.query(query, IssueReturnList);
    }

    @Override
    public List<Issue_Return> getUserBooks(String userId) {
        String query = "SELECT * FROM issue_return WHERE user_id = ?";
        RowMapper<Issue_Return> Issue_Return = new IssueReturnMapper();
        return jdbcTemplate.query(query, Issue_Return, userId);

    }
//    @Override
//    public List<UserIssuedBooks> getUserIssuedBooks(String userId) {
//        String query = "SELECT * FROM issue_return WHERE user_id = ? AND status = 'Issued'";
//        RowMapper<UserIssuedBooks> userIssuedBooks = new UserIssueMapper();
//        return jdbcTemplate.query(query, userIssuedBooks, userId);
//    }

    @Override
    public int issueBook(String id, String bookId) {
        String query1 = "SELECT COUNT(*) FROM users WHERE id = ? LIMIT 1";
        int count1 = jdbcTemplate.queryForObject(query1, new Object[] { Integer.parseInt(id) }, Integer.class);
        if(count1 > 0){
            String query2 = "SELECT COUNT(*) FROM books WHERE book_id = ? AND cur > 0";
            int count2 = jdbcTemplate.queryForObject(query2, new Object[] { Integer.parseInt(bookId) }, Integer.class);
            if(count2 > 0){
                String  query3 = "SELECT COUNT(*) FROM issue_return WHERE book_id = ? AND user_id = ? AND status = ?";
                int count3 = jdbcTemplate.queryForObject(query3, new Object[] { Integer.parseInt(bookId), Integer.parseInt(id), "Issued" }, Integer.class);
                if(count3 > 0){
                    return 4;
                }
                else{
                  String query4 = "INSERT INTO issue_return (user_id, book_id, issue_date, return_date, expected_return_date, fine, status) VALUES (:user_id, :book_id, :issue_date, :return_date, :expected_return_date, :fine, :status)";
                  MapSqlParameterSource newIssueReturn = new MapSqlParameterSource();
                  newIssueReturn.addValue("user_id", Integer.parseInt(id));
                  newIssueReturn.addValue("book_id", Integer.parseInt(bookId));

                  Date date = new Date();
                  Calendar cal = Calendar.getInstance();
                  cal.setTime(date);
                  cal.add(Calendar.DAY_OF_MONTH, -10);

                  newIssueReturn.addValue("issue_date", cal.getTime());

                  newIssueReturn.addValue("return_date", null);

                  cal.setTime(date);
                  cal.add(Calendar.DAY_OF_MONTH, -5);

                  newIssueReturn.addValue("expected_return_date", cal.getTime());
                  newIssueReturn.addValue("fine", 0);
                  newIssueReturn.addValue("status", "Issued");

                  int count4 = namedParameterJdbcTemplate.update(query4, newIssueReturn);
                    if(count4 > 0){
                        String query5 = "Update books SET cur = cur - 1 WHERE book_id = ?";
                        jdbcTemplate.update(query5, Integer.parseInt(bookId));
                        return 5;
                    }
                    else return 3;
                }
            }
            else{
                return 2;
            }
        }
        else{
            return 1;
        }

    }

    @Override
    public int returnBook(String id, String bookId) {
        String query1 = "SELECT COUNT(*) FROM issue_return WHERE user_id = ? AND book_id = ?";
        int count1 = jdbcTemplate.queryForObject(query1, new Object[] { Integer.parseInt(id), Integer.parseInt(bookId) }, Integer.class);
        if(count1 > 0){
            String query2 = "UPDATE issue_return SET status = ?, return_date = ? WHERE user_id = ? AND book_id = ?";
            Date date = new Date();
            int count2 = jdbcTemplate.update(query2, "Returned", date, Integer.parseInt(id), Integer.parseInt(bookId));
            if(count2 > 0){
                String query4 = "Update books SET cur = cur + 1 WHERE book_id = ?";
                jdbcTemplate.update(query4, Integer.parseInt(bookId));
                return 2;
            }
            else{
                return 1;
            }
        }
        else {
            return 0;
        }
    }

    @Override
    public void updateFine() {
        Date date = new Date();
        String query = "UPDATE issue_return SET fine = CASE WHEN DATEDIFF(:date, expected_return_date) > 0 THEN DATEDIFF(:date, expected_return_date)*10 ELSE 0 END WHERE status = 'Issued'";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("date", date);

        namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
    }


}
