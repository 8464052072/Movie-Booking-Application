package com.example.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private Long customerId;
    private Long movieId;
    private Long scheduleId;
}