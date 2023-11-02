package com.movielib.backend.repository;

import com.movielib.backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByMovieId(Long movie_id);

    List<Review> findAllByUserId(Long user_id);


}
