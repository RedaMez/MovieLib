package com.movielib.backend.config;

import com.movielib.backend.model.Movie;
import com.movielib.backend.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MovieConfig {

    @Bean
    CommandLineRunner commandLineRunner(MovieRepository repository) {
        return args -> {
            Movie movie1 = new Movie(
                    "Alien",
                    "SF",
                    "The crew of a spacecraft, Nostromo, intercept a distress signal from a planet" +
                            " and set out to investigate it. However, to their horror, they are attacked by" +
                            " an alien which later invades their ship.",
                    1979,
                    8.5,
                    "Ridley Scott"
            );

            Movie movie2 = new Movie(
                    "The godfather",
                    "Drama",
                    "Don Vito Corleone, head of a mafia family, decides to hand over his empire " +
                            "to his youngest son, Michael. However, his decision unintentionally puts the " +
                            "lives of his loved ones in grave danger.",
                    1972,
                    9.2,
                    "Francis Ford Coppola"
            );

            repository.saveAll(
                    List.of(movie1, movie2)
            );
        };
    }
}
