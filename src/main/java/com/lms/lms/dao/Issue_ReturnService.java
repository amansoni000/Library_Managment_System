package com.lms.lms.dao;


import com.lms.lms.entiry.Issue_Return;

import java.util.List;

public interface Issue_ReturnService {
    List<Issue_Return> getAllIssuedBooks();

    List<Issue_Return> getUserBooks(String userId);

    int issueBook(String id, String bookId);

    int returnBook(String id, String bookId);

    void updateFine();

//    List<UserIssuedBooks> getUserIssuedBooks(String userId);
}
