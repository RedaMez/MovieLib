package com.movielib.backend.services.impl;

import com.movielib.backend.dto.MovieDTO;
import com.movielib.backend.mapper.MovieMapper;
import com.movielib.backend.model.*;
import com.movielib.backend.repository.CategoryRepository;
import com.movielib.backend.repository.MovieRepository;
import com.movielib.backend.repository.UserRepository;
import com.movielib.backend.services.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.movielib.backend.mapper.MovieMapper.mapToMovie;
import static com.movielib.backend.mapper.MovieMapper.mapToMovieDTO;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<MovieDTO> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(MovieMapper::mapToMovieDTO).collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> searchMovies(String title) {
        List<Movie> movies = movieRepository.findMoviesByTitleContainsIgnoreCase(title);
        return movies.stream().map(MovieMapper::mapToMovieDTO).collect(Collectors.toList());
    }

    @Override
    public MovieDTO findMovieById(long id) {
        Optional<Movie> movieOptional = movieRepository
                .findById(id);
        if(movieOptional.isEmpty()){
            throw new IllegalStateException("movie doesn't exists");
        }
        return mapToMovieDTO(movieOptional.get());
    }

    @Override
    public List<MovieDTO> findMovieOrderByReleaseDate() {
        List<Movie> movies = movieRepository.findAllByOrderByReleaseDateDesc();
        return movies.stream().map(MovieMapper::mapToMovieDTO).collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findMovieOrderByRating() {
        List<Movie> movies = movieRepository.findAllByOrderByRatingDesc();
        return movies.stream().map(MovieMapper::mapToMovieDTO).collect(Collectors.toList());
    }

    @Override
    public void saveMovie(MovieDTO movieDTO) {
        Movie movie = mapToMovie(movieDTO);
        Optional<Movie> movieOptional = movieRepository
                .findMovieByTitleAndDirector(movie.getTitle(), movie.getDirector());
        if(movieOptional.isPresent()){
            throw new IllegalStateException("movie already exists");
        }
        Category c = movie.getCategory();
        if(categoryRepository.findById(c.getId()).isEmpty()){
            categoryRepository.save(c);
        }
        movieRepository.save(movie);
    }

    @Override
    @Transactional
    public void updateMovie(MovieDTO movieDTO) {
        Movie movie = mapToMovie(movieDTO);
        Category c = movie.getCategory();
        if(categoryRepository.findById(c.getId()).isEmpty()){
            categoryRepository.save(c);
        }
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(long movieId) {
        boolean exists = movieRepository.existsById(movieId);
        if(!exists){
            throw new IllegalStateException("movie with id "+ movieId+" does not exists");
        }
        movieRepository.deleteById(movieId);

    }

}
