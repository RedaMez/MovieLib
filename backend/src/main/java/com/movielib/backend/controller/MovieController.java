package com.movielib.backend.controller;

import com.movielib.backend.model.Movie;
import com.movielib.backend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/list")
    public List<Movie> getMovieList(){
        return movieService.getMovie();
    }

    @GetMapping("/{movieName}")
    public Movie getMovieInformation(@PathVariable("movieName") String title){
        return movieService.getMovieByTitle(title);
    }

    @GetMapping("/order_by_release_date")
    public List<Movie> getMovieListOrderByReleaseDate(){
        return movieService.getMovieOrderByReleaseDate();
    }

    @GetMapping("/order_by_rating")
    public List<Movie> getMovieListOrderByRating(){
        return movieService.getMovieOrderByRating();
    }

    @PostMapping
    public void registerNewMovie(@RequestBody Movie movie){
        movieService.addNewMovie(movie);
    }

    @DeleteMapping(path = "/delete_{movieId}")
    public void deleteMovie(@PathVariable("movieId") Long movieId){
        movieService.deleteMovie(movieId);
    }

    @PutMapping(path = "/update_{movieId}")
    public void updateMovie(
            @PathVariable("movieId") long movieId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String summary){
        movieService.updateMovie(movieId, title, summary);
    }
}
