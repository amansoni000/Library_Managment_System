package com.lms.lms.entiry;

import lombok.*;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    private int id;
    private String name;
    private String gender;
    private String contactNo;

    private List<Books> issuedList;

}
