package com.lms.lms.entiry;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Issue_Return {
    private int bookId;
    private int user_id;
    private Date issueDate;
    private Date returnDate;
    private String status;
    private Date expected_return_date;
    private int fine;


}
