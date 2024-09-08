package com.example.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.CustomerDTO;


@FeignClient(name = "customer-service", url = "http://localhost:8091")
public interface CustomerServiceClient {
    @GetMapping("/customers/{id}")
    CustomerDTO getCustomerById(@PathVariable Long id);
}
