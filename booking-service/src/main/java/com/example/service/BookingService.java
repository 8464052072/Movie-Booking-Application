package com.example.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.BookingRequest;
import com.example.dto.CustomerDTO;
import com.example.dto.MovieDTO;
import com.example.model.Booking;
import com.example.openFeign.CustomerServiceClient;
import com.example.openFeign.MovieServiceClient;
import com.example.openFeign.PricingServiceClient;
import com.example.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MovieServiceClient movieServiceClient;  // Feign Client to interact with MovieService

    @Autowired
    private PricingServiceClient pricingServiceClient; // Feign Client to interact with PricingService

    @Autowired
    private CustomerServiceClient customerServiceClient; // Feign Client to interact with CustomerService

    // 1. Create a new booking
    public Booking createBooking(BookingRequest bookingRequest) {
        // Get customer details from CustomerService
        CustomerDTO customer = customerServiceClient.getCustomerById(bookingRequest.getCustomerId());

        // Get movie details from MovieService
        MovieDTO movie = movieServiceClient.getMovieById(bookingRequest.getMovieId());

        // Calculate pricing from PricingService
        double price = pricingServiceClient.getPrice(bookingRequest.getMovieId(), bookingRequest.getScheduleId());

        // Create a new booking entity
        Booking booking = new Booking();
        booking.setCustomerId(customer.getId());
        booking.setMovieId(movie.getId());
        booking.setScheduleId(bookingRequest.getScheduleId());
        booking.setBookingDate(LocalDateTime.now());
        booking.setPrice(price);
        booking.setStatus("CONFIRMED");

        return bookingRepository.save(booking);
    }

    // 2. Get booking by ID
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    // 3. Get all bookings for a customer
    public List<Booking> getBookingsByCustomerId(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    // 4. Cancel a booking
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
    }
}