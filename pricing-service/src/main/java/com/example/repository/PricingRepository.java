package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Pricing;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {

    // Find pricing rule by movieId and scheduleId
    Optional<Pricing> findByMovieIdAndScheduleId(Long movieId, Long scheduleId);
}
