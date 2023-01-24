package com.lms.lms.mappers;

import com.lms.lms.entiry.Books;
import com.lms.lms.entiry.Users;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserMapper implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users Users = new Users();
        Users.setId(rs.getInt("id"));
        Users.setName(rs.getString("name"));
        Users.setGender(rs.getString("gender"));
        Users.setContactNo(rs.getString("contact_no"));
        return Users;
    }

}
