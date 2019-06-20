package com.mtmn.training.springjunit5.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntry {

    private String username;
    private Date date;
    private String review;
}
