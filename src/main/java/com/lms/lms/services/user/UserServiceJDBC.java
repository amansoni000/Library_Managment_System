package com.lms.lms.services.user;

import com.lms.lms.entiry.Books;
import com.lms.lms.entiry.Users;
//import com.lms.lms.mappers.UserMapper;
import com.lms.lms.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;


import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserServiceJDBC implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Users> getAllUsers() {
        String query = " select * from users u LEFT join issue_return ir on ir.user_id =u.id LEFT join books b on ir.book_id =b.book_id";
        return jdbcTemplate.query(query, new ResultSetExtractor<List<Users>>() {
            @Override
            public List<Users> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<Integer, Users> map = new HashMap<>();

                while(rs.next()){
                    Users users = map.getOrDefault(rs.getInt("id"), new Users());
                    users.setId(rs.getInt("id"));
                    users.setName(rs.getString("name"));
                    users.setContactNo(rs.getString("contact_no"));
                    users.setGender(rs.getString("gender"));

                    String temp =  rs.getString("status");
                    if(temp == null){
                        map.put(rs.getInt("id"), users);
                        continue;
                    }
                    if(temp.equals("Returned")) continue;

                    List<Books> book_list = users.getIssuedList() == null ? new ArrayList<>() : users.getIssuedList();
                    Books books = new Books();


                    books.setBook_id(rs.getInt("book_id"));
                    books.setTitle(rs.getString("title"));
                    books.setFine(rs.getInt("fine"));
                    books.setAuthor(rs.getString("author"));
                    book_list.add(books);

                    users.setIssuedList(book_list);
                    map.put(rs.getInt("user_id"), users);
                };

                List<Users> resultList = new ArrayList<>(map.values());
                return resultList;
            }
        });
    }

    @Override
    public Users getUser(String id) {
        String query = " SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.query(query, new Object[] {Integer.parseInt(id)}, new ResultSetExtractor<Users>() {
            @Override
            public Users extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    Users user = new Users();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setGender(rs.getString("gender"));
                    user.setContactNo(rs.getString("contact_no"));
                    return user;
                } else {
                    return null;
                }
            }
        });
    }

    @Override
    public int addUser(Users users) {
        String query = "INSERT INTO users (id, name, gender, contact_no) VALUES (:id, :name, :gender, :contact_no)";
        MapSqlParameterSource newUser = new MapSqlParameterSource();
        newUser.addValue("id", users.getId());
        newUser.addValue("name", users.getName());
        newUser.addValue("gender", users.getGender());
        newUser.addValue("contact_no", users.getContactNo());
        return namedParameterJdbcTemplate.update(query, newUser);
    }

    @Override
    public int updateUser(Users users) {
        String query = " UPDATE users SET name = :name, gender = :gender, contact_no = :contact_no WHERE id = :id";
        MapSqlParameterSource newUser = new MapSqlParameterSource();
        newUser.addValue("id", users.getId());
        newUser.addValue("name", users.getName());
        newUser.addValue("gender", users.getGender());
        newUser.addValue("contact_no", users.getContactNo());
        return namedParameterJdbcTemplate.update(query, newUser);
    }

    @Override
    public void deleteUser(int parseInt) {
        String query = " DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(query, parseInt);
    }
}
