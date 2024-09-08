package com.example.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.BookingDTO;
import com.example.dto.CustomerLoginRequest;
import com.example.dto.CustomerRegistrationRequest;
import com.example.model.Customer;
import com.example.openFeign.BookingServiceClient;
import com.example.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // To encrypt passwords
    
    @Autowired
    private BookingServiceClient bookingServiceClient; // Feign Client for BookingService

    // Register a new customer
    public Customer registerCustomer(CustomerRegistrationRequest request) {
        // Check if email is already taken
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already registered.");
        }

        // Encrypt the password before saving
        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        // Create and save a new customer
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPassword(encryptedPassword);
        customer.setRole("USER"); // Default role as USER
        customer.setRegisteredAt(LocalDateTime.now());

        return customerRepository.save(customer);
    }

    // Login customer and return user details on successful login
    public Customer loginCustomer(CustomerLoginRequest request) {
        // Check if the customer exists
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Validate the password
        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            throw new RuntimeException("Invalid credentials.");
        }

        // If credentials are correct, return customer details (without the password)
        return customer;
    }

    // Retrieve customer by ID
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    // Update customer profile
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Update customer fields
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());

        // Update password only if it's being changed
        if (updatedCustomer.getPassword() != null && !updatedCustomer.getPassword().isEmpty()) {
            customer.setPassword(passwordEncoder.encode(updatedCustomer.getPassword()));
        }

        return customerRepository.save(customer);
    }

    // Get customer bookings from BookingService via FeignClient
    public List<BookingDTO> getCustomerBookings(Long customerId) {
        return bookingServiceClient.getBookingsByCustomerId(customerId);
    }
}
