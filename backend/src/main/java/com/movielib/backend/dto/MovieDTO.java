package com.movielib.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private long id;
    @NotEmpty(message = "Movie title should not be empty")
    private String title;
    @NotEmpty(message = "Image link should not be empty")
    private String imageUrl;
    @NotEmpty(message = "Movie category should not be empty")
    private String category;
    private String summary;
    @NotNull(message = "Release date should not be null")
    @Min(value = 1900, message = "Release date should be at least 1900")
    @Max(value = 2023, message = "Release date should be at most 2023")
    private Integer releaseDate;
    @Builder.Default
    private double rating = 1.;
    @NotEmpty(message = "Movie director should not be empty")
    private String director;
    private List<String> actors;
    private List<ReviewDTO> reviews;
}


