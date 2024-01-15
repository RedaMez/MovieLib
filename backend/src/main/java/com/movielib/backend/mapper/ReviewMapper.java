package com.movielib.backend.mapper;

import com.movielib.backend.dto.ReviewDTO;
import com.movielib.backend.model.Review;

import static com.movielib.backend.mapper.UserMapper.mapToUser;
import static com.movielib.backend.mapper.UserMapper.mapToUserDTO;

public class ReviewMapper {



    public static Review mapToReview(ReviewDTO reviewDTO){
        return Review.builder()
                .id(reviewDTO.getId())
                .userEntity(mapToUser(reviewDTO.getUser()))
                .rating(reviewDTO.getRating())
                .comment(reviewDTO.getComment())
                .build();
    }

    public static ReviewDTO mapToReviewDTO(Review review){
        return ReviewDTO.builder()
                .id(review.getId())
                .user(mapToUserDTO(review.getUserEntity()))
                .movie(review.getMovie().getTitle())
                .rating(review.getRating())
                .comment(review.getComment())
                .build();
    }
}
