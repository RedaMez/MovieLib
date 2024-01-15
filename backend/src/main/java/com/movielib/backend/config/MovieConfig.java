package com.movielib.backend.config;

import com.movielib.backend.model.*;
import com.movielib.backend.repository.CategoryRepository;
import com.movielib.backend.repository.MovieRepository;
import com.movielib.backend.repository.ReviewRepository;
import com.movielib.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MovieConfig {

    @Bean
    CommandLineRunner commandLineRunner(MovieRepository movieRepository,
                                        CategoryRepository categoryRepository,
                                        UserRepository userRepository,
                                        ReviewRepository reviewRepository) {
        return args -> {

            UserEntity userEntity = UserEntity.builder()
                    .username("reda")
                    .email("reda@email.com")
                    .password("1234")
                    .build();

            userRepository.save(userEntity);

            Category category1 = Category.builder()
                    .name("SF")
                    .build();

            Category category2 = Category.builder()
                    .name("Drama")
                    .build();

            Category category3 = Category.builder()
                    .name("Thriller")
                    .build();

            Category category4 = Category.builder()
                    .name("Horror")
                    .build();

            categoryRepository.saveAll(
                    List.of(category1, category2, category3, category4)
            );

            Movie movie1 = Movie.builder()
                    .title("Alien")
                    .imageUrl("https://i.ebayimg.com/images/g/7YkAAOSwDYZdlYwL/s-l1200.webp")
                    .category(category1)
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
                    .imageUrl("https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg")
                    .category(category2)
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

            Movie movie3 = Movie.builder()
                    .title("The Silence of the lambs")
                    .imageUrl("https://m.media-amazon.com/images/M/MV5BNjNhZTk0ZmEtNjJhMi00YzFlLWE1MmEtYzM1M2ZmMGMwMTU4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg")
                    .category(category3)
                    .summary(
                            "Clarice Starling, an FBI trainee, seeks help from Hannibal Lecter, a psychopathic serial" +
                                    " killer and former psychiatrist, in order to apprehend another murderer who " +
                                    "has been claiming female victims."
                    )
                    .releaseDate(1991)
                    .director("Jonathan Demme")
                    .actors(
                            new ArrayList<>(List.of(
                                    Actor.builder().name("Anthony Hopkins").build(),
                                    Actor.builder().name("Jodie Foster").build(),
                                    Actor.builder().name("Ted Levine").build()
                            ))
                    ).build();

            Movie movie4 = Movie.builder()
                    .title("The Shining")
                    .imageUrl("https://m.media-amazon.com/images/M/MV5BZWFlYmY2MGEtZjVkYS00YzU4LTg0YjQtYzY1ZGE3NTA5NGQxXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_FMjpg_UX1000_.jpg")
                    .category(category4)
                    .summary(
                            "ack and his family move into an isolated hotel with a violent past. Living in isolation," +
                                    " Jack begins to lose his sanity, which affects his family members."
                    )
                    .releaseDate(1980)
                    .director("Stanley Kubrick")
                    .actors(
                            new ArrayList<>(List.of(
                                    Actor.builder().name("Jack Nicholson").build(),
                                    Actor.builder().name("Shelley Duvall").build(),
                                    Actor.builder().name("Danny Lloyd").build()
                            ))
                    ).build();

            movieRepository.saveAll(
                    List.of(movie1, movie2, movie3, movie4)
            );

            Review review = Review.builder()
                    .userEntity(userEntity)
                    .movie(movie1)
                    .rating(4.9)
                    .comment("Amazing Movie!!!")
                    .build();

            //movie1.addReview(review);
            reviewRepository.save(review);
        };
    }
}
