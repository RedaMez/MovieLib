package com.movielib.backend.config;

import com.movielib.backend.model.Actor;
import com.movielib.backend.model.Category;
import com.movielib.backend.model.Movie;
import com.movielib.backend.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MovieConfig {

    @Bean
    CommandLineRunner commandLineRunner(MovieRepository repository) {
        return args -> {
            Movie movie1 = Movie.builder()
                    .title("Alien")
                    .category(Category.builder().name("SF").build())
                    .summary(
                            "The crew of a spacecraft, Nostromo, intercept a distress signal from a planet" +
                            " and set out to investigate it. However, to their horror, they are attacked by" +
                            " an alien which later invades their ship."
                    )
                    .releaseDate(1979)
                    .director("Ridley Scott")
                    .actors(
                            new ArrayList<>(List.of(
                                    Actor.builder().name("Sigourney Weaver").build(),
                                    Actor.builder().name("Tom Skerritt").build(),
                                    Actor.builder().name("Veronica Cartwright").build()
                            ))
                    ).build();

            Movie movie2 = Movie.builder()
                    .title("The Godfather")
                    .category(Category.builder().name("Drama").build())
                    .summary(
                            "Don Vito Corleone, head of a mafia family, decides to hand over his empire " +
                            "to his youngest son, Michael. However, his decision unintentionally puts the " +
                            "lives of his loved ones in grave danger."
                    )
                    .releaseDate(1972)
                    .director("Francis Ford Coppola")
                    .actors(
                            new ArrayList<>(List.of(
                                    Actor.builder().name("Al Pacino").build(),
                                    Actor.builder().name("Marlon Brando").build(),
                                    Actor.builder().name("James Caan").build()
                            ))
                    ).build();

            repository.saveAll(
                    List.of(movie1, movie2)
            );
        };
    }
}
