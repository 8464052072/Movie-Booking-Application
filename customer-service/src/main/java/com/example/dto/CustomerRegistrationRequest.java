package com.example.dto;

import lombok.Data;

@Data
public class CustomerRegistrationRequest {
    private String name;
    private String email;
    private String password;
}
