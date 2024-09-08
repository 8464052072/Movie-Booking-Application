package com.example.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    @Column(name = "schedule_id", nullable = false)
    private Long scheduleId;

    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "status", nullable = false)
    private String status; // e.g., CONFIRMED, CANCELLED
    
}
