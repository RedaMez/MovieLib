package com.movielib.backend.services;

import com.movielib.backend.model.Movie;
import com.movielib.backend.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovie(){
        return movieRepository.findAll();
    }

    public Movie getMovieByTitle(String title) {
        Optional<Movie> movieOptional = movieRepository
                .findMovieByTitle(title);
        if(movieOptional.isEmpty()){
            throw new IllegalStateException("movie doesn't exists");
        }
        return movieOptional.get();
    }

    public List<Movie> getMovieOrderByReleaseDate() {
        return movieRepository.findAllByOrderByReleaseDateDesc();
    }

    public List<Movie> getMovieOrderByRating() {
        return movieRepository.findAllByOrderByRatingDesc();
    }

    public void addNewMovie(Movie movie) {
        Optional<Movie> movieOptional = movieRepository
                .findMovieByTitleAndDirector(movie.getTitle(), movie.getDirector());
        if(movieOptional.isPresent()){
            throw new IllegalStateException("movie already exists");
        }
        movieRepository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        boolean exists = movieRepository.existsById(movieId);
        if(!exists){
            throw new IllegalStateException("movie with id "+ movieId+" does not exists");
        }
        movieRepository.deleteById(movieId);

    }

    @Transactional
    public void updateMovie(long movieId,
                            String title,
                            String summary) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalStateException(
                        "movie with id "+movieId+" does not exists"));
        if(title != null &&
                !title.isEmpty() &&
                !Objects.equals(movie.getTitle(), title)){
            movie.setTitle(title);
        }
        if(summary != null &&
                !summary.isEmpty() &&
                !Objects.equals(movie.getSummary(), summary)){
            movie.setSummary(summary);
        }

    }
}
