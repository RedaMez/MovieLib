package com.movielib.backend.services;

import com.movielib.backend.dto.MovieDTO;
import com.movielib.backend.model.Movie;

import java.util.List;

public interface MovieService {
    List<MovieDTO> findAllMovies();
    MovieDTO findMovieById(long id);
    List<MovieDTO> findMovieOrderByReleaseDate();
    List<MovieDTO> findMovieOrderByRating();
    void saveMovie(MovieDTO movieDTO);
    void updateMovie(MovieDTO movie);
    void deleteMovie(long movieId);
    List<MovieDTO> searchMovies(String title);
}
