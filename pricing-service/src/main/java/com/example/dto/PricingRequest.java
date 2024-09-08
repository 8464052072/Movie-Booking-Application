package com.example.dto;

import lombok.Data;

@Data
public class PricingRequest {

    private Long movieId;
    private Long scheduleId;
    private double price;
    
}
