package com.example.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.MovieDTO;


@FeignClient(name = "movie-service", url = "http://localhost:8090")
public interface MovieServiceClient {
    @GetMapping("/movies/{id}")
    MovieDTO getMovieById(@PathVariable Long id);
}
