package com.movielib.backend.services;

import com.movielib.backend.dto.MovieDTO;
import com.movielib.backend.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    void createReview(Long movieId, ReviewDTO reviewDTO);

    List<ReviewDTO> findAllReviews();

    ReviewDTO findReviewById(long id);

    void updateReview(Long reviewId, ReviewDTO reviewDTO);

    void deleteReview(Long reviewId);

    List<ReviewDTO> searchReviews(String username);
}
