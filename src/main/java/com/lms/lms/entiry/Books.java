package com.lms.lms.entiry;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Books {
    private int book_id;
    private int currCount;
    private int actualCount;
    private String title;
    private String author;
    private int fine;

}
