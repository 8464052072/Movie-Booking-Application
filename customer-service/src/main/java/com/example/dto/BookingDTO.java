package com.example.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingDTO {
    private Long id;
    private Long customerId;
    private Long movieId;
    private String movieName;
    private LocalDateTime bookingDate;

}
