package com.lms.lms.Controller;

import com.lms.lms.entiry.Issue_Return;
import com.lms.lms.dao.Issue_ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class IssueReturnController {

    @Autowired
    private Issue_ReturnService issueReturnService;

    @GetMapping("home/users/issue_return")
    public List<Issue_Return> getAllIssuedBooks(){
        return issueReturnService.getAllIssuedBooks();
    }

    @GetMapping("/home/users/issue_return/{user_id}")
    public List<Issue_Return> getUserBooks(@PathVariable String user_id){
        return issueReturnService.getUserBooks(user_id);
    }

//    @GetMapping("/home/users/issue_return/{user_id}/issued")
//    public List<UserIssuedBooks> getUserIssuedBooks(@PathVariable String user_id){
//        return issueReturnService.getUserIssuedBooks(user_id);
//    }
    @Scheduled(cron = "*/10 * * * * *")
    @GetMapping("/home/users/issue_return/fine")
    public void updateFine(){
        issueReturnService.updateFine();
    }

    @PostMapping("/home/users/issue_return/{user_id}/{book_id}")
    public String issueBook(@PathVariable("user_id") String id,@PathVariable("book_id")  String book_id){
        int result = issueReturnService.issueBook(id, book_id);
        if(result == 1){
            return "USER NOT REGISTERED";
        }
        else if(result == 2){
            return "Book Not Available";
        }
        else if(result == 3){
            return "ERROR";
        }
        else if(result == 4){
            return "Sorry book is already issued by user";
        }
        else{
            return "Success";
        }
    }

    @PutMapping("/home/users/issue_return/{user_id}/{book_id}")
    public String returnBook(@PathVariable("user_id") String id, @PathVariable("book_id") String book_id) throws ParseException {
        int result = issueReturnService.returnBook(id, book_id);
        if(result == 0){
            return "BOOK cant be returned";
        }
        else if(result == 1){
            return "Error";
        }
        else{
            return "Book returned success";
        }
    }
}
