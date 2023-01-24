package com.lms.lms.Controller;

import com.lms.lms.entiry.Users;
import com.lms.lms.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Getting all the users details with their respective issued books list
//    @GetMapping("issued")
//    public List<Users> getAllIssuedUsers() throws SQLException {
//        return userService.getAllIssuedUsers();
//    }

    // Getting all the user details
    @GetMapping
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    // Getting user detail by user id
    @GetMapping("{id}")
    public Users getUser(@PathVariable String id){
        return userService.getUser(id);
    }

    // Adding a User
    @PostMapping
    public int addUser(@RequestBody Users users){
        return userService.addUser(users);
    }

    // Updating user details
    @PutMapping
    public int updateUser(@RequestBody Users users){
        return userService.updateUser(users);
    }

    // Deleting the user details
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(Integer.parseInt(id));
    }

}
