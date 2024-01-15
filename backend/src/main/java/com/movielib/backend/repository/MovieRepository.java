package com.movielib.backend.repository;

import com.movielib.backend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findMovieByTitleAndDirector(String title, String director);

    List<Movie> findAllByOrderByReleaseDateDesc();

    List<Movie> findAllByOrderByRatingDesc();

    List<Movie> findMoviesByTitle(String title);

    List<Movie> findMoviesByTitleContainsIgnoreCase(String title);

}
