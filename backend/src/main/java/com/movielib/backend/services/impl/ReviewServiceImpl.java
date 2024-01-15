package com.movielib.backend.services.impl;

import com.movielib.backend.dto.ReviewDTO;
import com.movielib.backend.mapper.ReviewMapper;
import com.movielib.backend.model.Movie;
import com.movielib.backend.model.Review;
import com.movielib.backend.model.UserEntity;
import com.movielib.backend.repository.MovieRepository;
import com.movielib.backend.repository.ReviewRepository;
import com.movielib.backend.repository.UserRepository;
import com.movielib.backend.services.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.movielib.backend.mapper.ReviewMapper.mapToReview;
import static com.movielib.backend.mapper.ReviewMapper.mapToReviewDTO;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ReviewDTO> findAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(ReviewMapper::mapToReviewDTO).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO findReviewById(long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isEmpty()) {
            throw new IllegalStateException("review "+id+" doesn't exists");
        }
        return mapToReviewDTO(reviewOptional.get());
    }

    @Override
    @Transactional
    public void updateReview(Long reviewId, ReviewDTO reviewDTO) {
        List<Movie> movieOptional = movieRepository
                .findMoviesByTitle(reviewDTO.getMovie());
        if (movieOptional.isEmpty()) {
            throw new IllegalStateException("movie doesn't exists");
        }
        Movie movie = movieOptional.get(0);
        Optional<UserEntity> userOptional = userRepository.findUserEntityByUsername(reviewDTO.getUser().getUsername());
        if (userOptional.isEmpty()) {
            throw new IllegalStateException("user "+reviewDTO.getUser().getUsername()+" doesn't exists");
        }
        UserEntity userEntity = userOptional.get();
        Review review = mapToReview(reviewDTO);
        review.setUserEntity(userEntity);
        review.setMovie(movie);
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        boolean exists = reviewRepository.existsById(reviewId);
        if(!exists){
            throw new IllegalStateException("review with id "+ reviewId+" does not exists");
        }
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public List<ReviewDTO> searchReviews(String query) {
        List<Review> reviews = reviewRepository
                .findReviewsByUserEntity_UsernameContainsIgnoreCase(query);
        return reviews.stream().map(ReviewMapper::mapToReviewDTO).collect(Collectors.toList());
    }

    @Override
    public void createReview(Long movieId, ReviewDTO reviewDTO) {
        Optional<Movie> movieOptional = movieRepository
                .findById(movieId);
        if (movieOptional.isEmpty()) {
            throw new IllegalStateException("movie doesn't exists");
        }
        Movie movie = movieOptional.get();

        Optional<UserEntity> userOptional = userRepository.findUserEntityByUsername(reviewDTO.getUser().getUsername());
        if (userOptional.isEmpty()) {
            throw new IllegalStateException("user doesn't exists");
        }
        UserEntity userEntity = userOptional.get();
        Review review = mapToReview(reviewDTO);
        movie.addReview(review);
        review.setMovie(movie);
        review.setUserEntity(userEntity);
        reviewRepository.save(review);
    }
}
