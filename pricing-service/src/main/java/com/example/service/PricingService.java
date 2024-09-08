package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.PricingRequest;
import com.example.model.Pricing;
import com.example.repository.PricingRepository;

@Service
public class PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    // 1. Get price by movieId and scheduleId
    public double getPrice(Long movieId, Long scheduleId) {
        Pricing pricing = pricingRepository.findByMovieIdAndScheduleId(movieId, scheduleId)
                .orElseThrow(() -> new RuntimeException("Pricing not found for the given movie and schedule"));
        return pricing.getPrice();
    }

    // 2. Create new pricing rule
    public Pricing createPricing(PricingRequest pricingRequest) {
        Pricing pricing = new Pricing();
        pricing.setMovieId(pricingRequest.getMovieId());
        pricing.setScheduleId(pricingRequest.getScheduleId());
        pricing.setPrice(pricingRequest.getPrice());
        return pricingRepository.save(pricing);
    }

    // 3. Update existing pricing rule
    public Pricing updatePricing(Long id, PricingRequest pricingRequest) {
        Pricing pricing = pricingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pricing rule not found"));
        pricing.setMovieId(pricingRequest.getMovieId());
        pricing.setScheduleId(pricingRequest.getScheduleId());
        pricing.setPrice(pricingRequest.getPrice());
        return pricingRepository.save(pricing);
    }

    // 4. Delete pricing rule
    public void deletePricing(Long id) {
        Pricing pricing = pricingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pricing rule not found"));
        pricingRepository.delete(pricing);
    }
}
