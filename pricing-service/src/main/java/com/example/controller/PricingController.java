package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PricingRequest;
import com.example.model.Pricing;
import com.example.service.PricingService;

@RestController
@RequestMapping("/pricing")
public class PricingController {

    @Autowired
    private PricingService pricingService;

    // 1. Get price by movieId and scheduleId
    @GetMapping("/movie/{movieId}/schedule/{scheduleId}")
    public ResponseEntity<Double> getPrice(
            @PathVariable Long movieId,
            @PathVariable Long scheduleId) {
        double price = pricingService.getPrice(movieId, scheduleId);
        return ResponseEntity.ok(price);
    }

    // 2. Create new pricing rule
    @PostMapping
    public ResponseEntity<Pricing> createPricing(@RequestBody PricingRequest pricingRequest) {
        Pricing newPricing = pricingService.createPricing(pricingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPricing);
    }

    // 3. Update existing pricing rule
    @PutMapping("/{id}")
    public ResponseEntity<Pricing> updatePricing(@PathVariable Long id, @RequestBody PricingRequest pricingRequest) {
        Pricing updatedPricing = pricingService.updatePricing(id, pricingRequest);
        return ResponseEntity.ok(updatedPricing);
    }

    // 4. Delete pricing rule by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePricing(@PathVariable Long id) {
        pricingService.deletePricing(id);
        return ResponseEntity.ok("Pricing rule deleted successfully.");
    }
}