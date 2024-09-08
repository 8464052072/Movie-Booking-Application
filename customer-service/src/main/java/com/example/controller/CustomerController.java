package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookingDTO;
import com.example.dto.CustomerLoginRequest;
import com.example.dto.CustomerRegistrationRequest;
import com.example.model.Customer;
import com.example.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 1. Register a new customer
    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        Customer registeredCustomer = customerService.registerCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredCustomer);
    }

    // 2. Login a customer
    @PostMapping("/login")
    public ResponseEntity<Customer> loginCustomer(@RequestBody CustomerLoginRequest request) {
        Customer loggedInCustomer = customerService.loginCustomer(request);
        return ResponseEntity.ok(loggedInCustomer); // Returning customer info on successful login
    }

    // 3. Get customer profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    // 4. Update customer profile
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer updated = customerService.updateCustomer(id, updatedCustomer);
        return ResponseEntity.ok(updated);
    }

    // 5. Get customer bookings by customer ID
    @GetMapping("/{id}/bookings")
    public ResponseEntity<List<BookingDTO>> getCustomerBookings(@PathVariable Long id) {
        List<BookingDTO> bookings = customerService.getCustomerBookings(id);
        return ResponseEntity.ok(bookings);
    }
}