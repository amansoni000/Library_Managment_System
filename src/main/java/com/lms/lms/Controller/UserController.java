package com.lms.lms.Controller;

import com.lms.lms.entiry.Users;
import com.lms.lms.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/home/users/issued")
    public List<Users> getAllIssuedUsers() throws SQLException {
        return userService.getAllIssuedUsers();
    }
    @GetMapping("/home/users")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/home/users/{id}")
    public Users getUser(@PathVariable String id){
        return userService.getUser(id);
    }

    @PostMapping("home/users")
    public int addUser(@RequestBody Users users){
        return userService.addUser(users);
    }

    @PutMapping("home/users")
    public int updateUser(@RequestBody Users users){
        return userService.updateUser(users);
    }

    @DeleteMapping("home/users/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(Integer.parseInt(id));
    }

}
