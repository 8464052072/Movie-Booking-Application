package com.example.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pricing-service", url = "http://localhost:8093")
public interface PricingServiceClient {
    @GetMapping("/pricing/movie/{movieId}/schedule/{scheduleId}")
    double getPrice(@PathVariable Long movieId, @PathVariable Long scheduleId);
}
