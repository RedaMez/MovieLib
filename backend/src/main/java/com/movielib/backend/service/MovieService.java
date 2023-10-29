package com.movielib.backend.service;

import com.movielib.backend.model.Movie;
import com.movielib.backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {Service used by the controller to communicate with the view.}
 * It transforms the data from the model so that it fit the one used by the view.
 */
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
}
