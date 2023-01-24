package com.lms.lms.dao;

import com.lms.lms.entiry.Users;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserService {
//    public List<Users> getAllIssuedUsers() throws SQLException;

    Users getUser(String id);

    int addUser(Users users);

    int updateUser(Users users);

    void deleteUser(int parseInt);


    void updateBookList();

    List<Users> getAllUsers();
}
