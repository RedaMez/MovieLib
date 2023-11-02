package com.movielib.backend.repository;


import com.movielib.backend.model.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewRepositoryTest extends Assertions {

    ReviewRepository reviewRepository;

    @Autowired
    public ReviewRepositoryTest(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Test
    public void ReviewRepository_SaveAll_ReturnsSavedReview(){
        Review review = Review.builder()
                .userId(34535)
                .movieId(53453)
                .rating(8.5)
                .comment("nice movie!")
                .build();

        Review savedReview = reviewRepository.save(review);

        assertThat(savedReview).isNotNull();
        assertThat(savedReview.getId()).isGreaterThan(0);
    }

    @Test
    public void ReviewRepository_GetAll_ReturnMoreThanOneReview(){
        Review review = Review.builder()
                .userId(34535)
                .movieId(53453)
                .rating(8.5)
                .comment("nice movie!")
                .build();
        Review review2 = Review.builder()
                .userId(34535)
                .movieId(53453)
                .rating(8.5)
                .comment("nice movie!")
                .build();

        reviewRepository.save(review);
        reviewRepository.save(review2);

        List<Review> reviewList = reviewRepository.findAll();

        assertThat(reviewList).isNotNull();
        assertThat(reviewList.size()).isEqualTo(2);
    }

    @Test
    public void ReviewRepository_FindAllByMovieId_ReturnMoreThanOneReview(){
        Review review = Review.builder()
                .userId(34535)
                .movieId(53453)
                .rating(8.5)
                .comment("nice movie!")
                .build();
        Review review2 = Review.builder()
                .userId(34535)
                .movieId(53453)
                .rating(8.5)
                .comment("nice movie!")
                .build();
        Review review3 = Review.builder()
                .userId(34535)
                .movieId(53455)
                .rating(8.5)
                .comment("nice movie!")
                .build();

        reviewRepository.save(review);
        reviewRepository.save(review2);
        reviewRepository.save(review3);

        List<Review> reviewList = reviewRepository.findAllByMovieId(review.getMovieId());
        assertThat(reviewList).isNotNull();
        assertThat(reviewList.size()).isEqualTo(2);
    }

    @Test
    public void ReviewRepository_FindAllByUserId_ReturnMoreThanOneReview(){
        Review review = Review.builder()
                .userId(34535)
                .movieId(53453)
                .rating(8.5)
                .comment("nice movie!")
                .build();
        Review review2 = Review.builder()
                .userId(34535)
                .movieId(53453)
                .rating(8.5)
                .comment("nice movie!")
                .build();
        Review review3 = Review.builder()
                .userId(34534)
                .movieId(53455)
                .rating(8.5)
                .comment("nice movie!")
                .build();

        reviewRepository.save(review);
        reviewRepository.save(review2);
        reviewRepository.save(review3);

        List<Review> reviewList = reviewRepository.findAllByUserId(review.getUserId());
        assertThat(reviewList).isNotNull();
        assertThat(reviewList.size()).isEqualTo(2);
    }

    @Test
    public void ReviewRepository_FindById_ReturnsSavedReview(){
        Review review = Review
                .builder()
                .userId(34535)
                .movieId(53453)
                .rating(8.5)
                .comment("nice movie!")
                .build();

        reviewRepository.save(review);

        Review reviewReturn = reviewRepository.findById(review.getId()).get();

        assertThat(reviewReturn).isNotNull();
    }

    @Test
    public void ReviewRepository_UpdateReview_ReturnsReview(){
        Review review = Review.builder()
                .userId(34535)
                .movieId(53453)
                .rating(8.5)
                .comment("nice movie!")
                .build();

        reviewRepository.save(review);

        Review reviewSave = reviewRepository.findById(review.getId()).get();
        reviewSave.setComment("awful!");
        reviewSave.setRating(1.5);
        Review updatedReview = reviewRepository.save(reviewSave);

        assertThat(updatedReview.getComment()).isNotNull();
        assertThat(updatedReview.getRating()).isGreaterThan(.0);
    }

    @Test
    public void ReviewRepository_ReviewDelete_returnReviewIsEmpty() {
        Review review = Review.builder()
                .userId(34535)
                .movieId(53453)
                .rating(8.5)
                .comment("nice movie!")
                .build();

        reviewRepository.save(review);

        reviewRepository.deleteById(review.getId());
        Optional<Review> reviewReturn = reviewRepository.findById(review.getId());

        assertThat(reviewReturn).isEmpty();
    }
}
