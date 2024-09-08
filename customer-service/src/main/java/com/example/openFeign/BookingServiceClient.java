package com.example.openFeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.BookingDTO;


@FeignClient(name = "booking-service", url = "http://localhost:8092")
public interface BookingServiceClient {

    @GetMapping("/bookings/{customerId}")
    List<BookingDTO> getBookingsByCustomerId(@PathVariable Long customerId);
}