package com.movielib.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private long id;
    private UserEntityDTO user;
    private String movie;
    @NotNull(message = "You must rate the movie")
    @Min(value = 0, message = "Rating should be at least 0")
    @Max(value = 5, message = "Rating should be at most 5")
    private double rating;
    private String comment;
}
