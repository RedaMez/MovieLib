package com.movielib.backend.controller;

import com.movielib.backend.dto.MovieDTO;
import com.movielib.backend.model.Movie;
import com.movielib.backend.services.MovieService;
//import jakarta.validation.Valid;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // List each movie by order of creation or modification
    @GetMapping("/movies")
    public String getMovieList(Model model){
        List<MovieDTO> movies = movieService.findAllMovies();
        model.addAttribute("movies", movies);
        return "movies-list";
    }

    @GetMapping("/movies/order_by_release_date")
    public String getMovieListOrderByReleaseDate(Model model){
        List<MovieDTO> movies = movieService.findMovieOrderByReleaseDate();
        model.addAttribute("movies", movies);
        return "movies-list";
    }

    @GetMapping("/movies/order_by_rating")
    public String getMovieListOrderByRating(Model model){
        List<MovieDTO> movies = movieService.findMovieOrderByRating();
        model.addAttribute("movies", movies);
        return "movies-list";
    }

    @GetMapping("/movies/{movieId}")
    public String getMovieDetail(@PathVariable("movieId") long id, Model model){
        MovieDTO movie = movieService.findMovieById(id);
        model.addAttribute("movie", movie);
        return "movies-detail";
    }

    @GetMapping("/movies/new")
    public String createMovieForm(Model model){
        Movie movie = Movie.builder().build();
        model.addAttribute("movie", movie);
        return "movies-create";
    }

    @PostMapping("/movies/new")
    public String saveMovie(@Valid @ModelAttribute("movie") MovieDTO movieDTO,
                            BindingResult result,
                            Model model){
        if(result.hasErrors()){
            model.addAttribute("movie", movieDTO);
            return "movies-create";
        }
        movieService.saveMovie(movieDTO);
        return "redirect:/movies";
    }

    @GetMapping("/movies/{movieId}/edit")
    public String editMovieForm(@PathVariable("movieId") long movieId, Model model){
        MovieDTO movie = movieService.findMovieById(movieId);
        model.addAttribute("movie", movie);
        return "movies-edit";
    }

    @PostMapping(path = "/movies/{movieId}/edit")
    public String updateMovie(@PathVariable("movieId") long movieId,
                              @Valid @ModelAttribute("movie") MovieDTO movie,
                              Model model,
                              BindingResult result){
        if(result.hasErrors()) {
            System.out.println("ERREUR ICI");
            model.addAttribute("movie", movie);
            return "movies-edit";
        }
        movie.setId(movieId);
        movieService.updateMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping(path = "/movies/{movieId}/delete")
    public String deleteMovie(@PathVariable("movieId") Long movieId){
        movieService.deleteMovie(movieId);
        return "redirect:/movies";
    }

    @GetMapping("/movies/search")
    public String searchMovie(@RequestParam(value = "query") String query, Model model){
        List<MovieDTO> movies = movieService.searchMovies(query);
        model.addAttribute("movies", movies);
        return "movies-list";
    }
}
