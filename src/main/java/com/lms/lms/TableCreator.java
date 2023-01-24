package com.lms.lms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
@Service
public class TableCreator implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbctemplate;

    @Override
    public void run(String... args) throws Exception {
        String query1 = " CREATE TABLE IF NOT EXISTS books(book_id int primary key, cur int , actual int, title varchar(255), author varchar(255) )";
        String query2 = " CREATE TABLE IF NOT EXISTS users(id int primary key, name varchar(255), gender varchar(255) , contact_no varchar(255) )";
        String query3 = " CREATE TABLE IF NOT EXISTS issue_return(idx int AUTO_INCREMENT PRIMARY KEY, user_id int, book_id int, issue_date DATE, expected_return_date DATE, return_date DATE, status varchar(255), fine int)";
        jdbctemplate.update(query1);
        jdbctemplate.update(query2);
        jdbctemplate.update(query3);

    }

}
