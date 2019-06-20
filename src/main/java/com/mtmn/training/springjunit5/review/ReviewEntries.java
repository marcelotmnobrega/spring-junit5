package com.mtmn.training.springjunit5.review;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ReviewEntries {

    private String username;
    private Date date;
    private String review;
}
