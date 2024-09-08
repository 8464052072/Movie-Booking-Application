package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Find all reviews by movieId
    List<Review> findByMovieId(Long movieId);
}
