package com.example.dto;

import lombok.Data;

@Data
public class ReviewRequest {

    private Long movieId;
    private Long customerId;
    private int rating;
    private String comment;
   
}
