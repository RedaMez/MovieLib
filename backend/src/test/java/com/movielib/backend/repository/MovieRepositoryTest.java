package com.movielib.backend.repository;

import com.movielib.backend.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryTest extends Assertions {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void MovieRepository_SaveAll_ReturnSavedMovie(){
        Movie movie = Movie.builder()
                .title("Goodfellas")
                .category(Category.builder().name("Drama").build())
                .summary("Henry grows up idolising mobsters in his impoverished neighbourhood. Things take" +
                        " a turn for the worse when he along with his friends Jimmy and Tommy decide to make" +
                        " their way up the mob hierarchy.")
                .releaseDate(1111)
                .director("Martin Scorsese")
                .actors(
                        new ArrayList<>(List.of(
                                Actor.builder().name("Ray Liotta").build(),
                                Actor.builder().name("Robert De Niro").build(),
                                Actor.builder().name("Joe Pesci").build()
                        ))
                )
                .reviews(new ArrayList<>())
                .build();

        Movie savedMovie = movieRepository.save(movie);

        assertThat(savedMovie).isNotNull();
        assertThat(savedMovie.getId()).isGreaterThan(0);
    }

    @Test
    public void MovieRepository_GetAll_returnMoreThanOneMovie(){
        Movie movie1 = Movie.builder()
                .title("Goodfellas")
                .category(Category.builder().name("Drama").build())
                .summary("Henry grows up idolising mobsters in his impoverished neighbourhood. Things take" +
                        " a turn for the worse when he along with his friends Jimmy and Tommy decide to make" +
                        " their way up the mob hierarchy.")
                .releaseDate(1111)
                .director("Martin Scorsese")
                .actors(
                        new ArrayList<>(List.of(
                                Actor.builder().name("Ray Liotta").build(),
                                Actor.builder().name("Robert De Niro").build(),
                                Actor.builder().name("Joe Pesci").build()
                        ))
                )
                .reviews(new ArrayList<>())
                .build();
        Movie movie2 = Movie.builder()
                .title("Goodfellas")
                .category(Category.builder().name("Drama").build())
                .summary("Henry grows up idolising mobsters in his impoverished neighbourhood. Things take" +
                        " a turn for the worse when he along with his friends Jimmy and Tommy decide to make" +
                        " their way up the mob hierarchy.")
                .releaseDate(1111)
                .director("Martin Scorsese")
                .actors(
                        new ArrayList<>(List.of(
                                Actor.builder().name("Ray Liotta").build(),
                                Actor.builder().name("Robert De Niro").build(),
                                Actor.builder().name("Joe Pesci").build()
                        ))
                )
                .reviews(new ArrayList<>())
                .build();

        movieRepository.save(movie1);
        movieRepository.save(movie2);

        List<Movie> movieList = movieRepository.findAll();

        assertThat(movieList).isNotNull();
        assertThat(movieList.size()).isEqualTo(2);
    }

    @Test
    public void MovieRepository_FindById_returnMovie(){
        Movie movie = Movie.builder()
                .title("Goodfellas")
                .category(Category.builder().name("Drama").build())
                .summary("Henry grows up idolising mobsters in his impoverished neighbourhood. Things take" +
                        " a turn for the worse when he along with his friends Jimmy and Tommy decide to make" +
                        " their way up the mob hierarchy.")
                .releaseDate(1111)
                .director("Martin Scorsese")
                .actors(
                        new ArrayList<>(List.of(
                                Actor.builder().name("Ray Liotta").build(),
                                Actor.builder().name("Robert De Niro").build(),
                                Actor.builder().name("Joe Pesci").build()
                        ))
                )
                .reviews(new ArrayList<>())
                .build();

        movieRepository.save(movie);

        Optional<Movie> optionalMovie = movieRepository.findById(movie.getId());

        assertThat(optionalMovie).isNotNull();
    }

    @Test
    public void MovieRepository_FindByTitle_returnMovieNotNull(){
        Movie movie = Movie.builder()
                .title("Goodfellas")
                .category(Category.builder().name("Drama").build())
                .summary("Henry grows up idolising mobsters in his impoverished neighbourhood. Things take" +
                        " a turn for the worse when he along with his friends Jimmy and Tommy decide to make" +
                        " their way up the mob hierarchy.")
                .releaseDate(1111)
                .director("Martin Scorsese")
                .actors(
                        new ArrayList<>(List.of(
                                Actor.builder().name("Ray Liotta").build(),
                                Actor.builder().name("Robert De Niro").build(),
                                Actor.builder().name("Joe Pesci").build()
                        ))
                )
                .reviews(new ArrayList<>())
                .build();

        movieRepository.save(movie);

        List<Movie> movieList = movieRepository.findMoviesByTitle(movie.getTitle());

        assertThat(movieList.size()).isEqualTo(1);
    }

    @Test
    public void MovieRepository_FindByTitleAndDirector_returnMovieNotNull(){
        Movie movie = Movie.builder()
                .title("Goodfellas")
                .category(Category.builder().name("Drama").build())
                .summary("Henry grows up idolising mobsters in his impoverished neighbourhood. Things take" +
                        " a turn for the worse when he along with his friends Jimmy and Tommy decide to make" +
                        " their way up the mob hierarchy.")
                .releaseDate(1111)
                .director("Martin Scorsese")
                .actors(
                        new ArrayList<>(List.of(
                                Actor.builder().name("Ray Liotta").build(),
                                Actor.builder().name("Robert De Niro").build(),
                                Actor.builder().name("Joe Pesci").build()
                        ))
                )
                .reviews(new ArrayList<>())
                .build();

        movieRepository.save(movie);

        Optional<Movie> optionalMovie = movieRepository.findMovieByTitleAndDirector(movie.getTitle(), movie.getDirector());

        assertThat(optionalMovie).isNotNull();
    }

    @Test
    public void MovieRepository_OrderByReleaseDate_returnOrderedMovies(){
        Movie movie1 = Movie.builder()
                .title("Goodfellas")
                .category(Category.builder().name("Drama").build())
                .summary("Henry grows up idolising mobsters in his impoverished neighbourhood. Things take" +
                        " a turn for the worse when he along with his friends Jimmy and Tommy decide to make" +
                        " their way up the mob hierarchy.")
                .releaseDate(1111)
                .director("Martin Scorsese")
                .actors(
                        new ArrayList<>(List.of(
                                Actor.builder().name("Ray Liotta").build(),
                                Actor.builder().name("Robert De Niro").build(),
                                Actor.builder().name("Joe Pesci").build()
                        ))
                )
                .reviews(new ArrayList<>())
                .build();
        Movie movie2 = Movie.builder()
                .title("Goodfellas")
                .category(Category.builder().name("Drama").build())
                .summary("Henry grows up idolising mobsters in his impoverished neighbourhood. Things take" +
                        " a turn for the worse when he along with his friends Jimmy and Tommy decide to make" +
                        " their way up the mob hierarchy.")
                .releaseDate(2000)
                .director("Martin Scorsese")
                .actors(
                        new ArrayList<>(List.of(
                                Actor.builder().name("Ray Liotta").build(),
                                Actor.builder().name("Robert De Niro").build(),
                                Actor.builder().name("Joe Pesci").build()
                        ))
                )
                .reviews(new ArrayList<>())
                .build();

        movieRepository.save(movie1);
        movieRepository.save(movie2);

        List<Movie> movieList = movieRepository.findAllByOrderByReleaseDateDesc();

        assertThat(movieList).isNotNull();
        for(int i = 0; i<movieList.size()-1; i++){
            int j = i+1;
            assertThat(movieList.get(i).getReleaseDate()).isGreaterThanOrEqualTo(movieList.get(j).getReleaseDate());
        }
    }

    @Test
    public void MovieRepository_OrderByRating_returnOrderedMovies(){
        Movie movie1 = Movie.builder()
                .title("Goodfellas")
                .category(Category.builder().name("Drama").build())
                .summary("Henry grows up idolising mobsters in his impoverished neighbourhood. Things take" +
                        " a turn for the worse when he along with his friends Jimmy and Tommy decide to make" +
                        " their way up the mob hierarchy.")
                .releaseDate(1111)
                .director("Martin Scorsese")
                .actors(
                        new ArrayList<>(List.of(
                                Actor.builder().name("Ray Liotta").build(),
                                Actor.builder().name("Robert De Niro").build(),
                                Actor.builder().name("Joe Pesci").build()
                        ))
                )
                .reviews(new ArrayList<>())
                .build();
        Movie movie2 = Movie.builder()
                .title("Goodfellas")
                .category(Category.builder().name("Drama").build())
                .summary("Henry grows up idolising mobsters in his impoverished neighbourhood. Things take" +
                        " a turn for the worse when he along with his friends Jimmy and Tommy decide to make" +
                        " their way up the mob hierarchy.")
                .releaseDate(2000)
                .director("Martin Scorsese")
                .actors(
                        new ArrayList<>(List.of(
                                Actor.builder().name("Ray Liotta").build(),
                                Actor.builder().name("Robert De Niro").build(),
                                Actor.builder().name("Joe Pesci").build()
                        ))
                )
                .reviews(new ArrayList<>())
                .build();

        UserEntity u = UserEntity.builder()
                .username("username")
                .email("eeeeeee")
                .password("ppppppp")
                .build();

        movie1.addReview(Review.builder()
                .userEntity(u)
                .movie(movie1)
                .rating(9.)
                .comment("amazing!")
                .build());
        movie2.addReview(Review.builder()
                .userEntity(u)
                .movie(movie2)
                .rating(2.5)
                .comment("awful!")
                .build());

        movieRepository.save(movie1);
        movieRepository.save(movie2);

        List<Movie> movieList = movieRepository.findAllByOrderByRatingDesc();

        assertThat(movieList).isNotNull();
        for(int i = 0; i<movieList.size()-1; i++){
            int j = i+1;
            assertThat(movieList.get(i).getRating()).isGreaterThanOrEqualTo(movieList.get(j).getRating());
        }
    }

    @Test
    public void MovieRepository_UpdateMovie_returnMovieNotNull(){
        Movie movie = Movie.builder()
                .title("Goodfellas")
                .category(Category.builder().name("Drama").build())
                .summary("Henry grows up idolising mobsters in his impoverished neighbourhood. Things take" +
                        " a turn for the worse when he along with his friends Jimmy and Tommy decide to make" +
                        " their way up the mob hierarchy.")
                .releaseDate(1111)
                .director("Martin Scorsese")
                .actors(
                        new ArrayList<>(List.of(
                                Actor.builder().name("Ray Liotta").build(),
                                Actor.builder().name("Robert De Niro").build(),
                                Actor.builder().name("Joe Pesci").build()
                        ))
                )
                .reviews(new ArrayList<>())
                .build();

        movieRepository.save(movie);

        Movie movieSaved = movieRepository.findById(movie.getId()).get();
        movieSaved.setTitle("The Godfather");
        movieSaved.setCategory(Category.builder().name("Horror").build());

        Movie updatedMovie = movieRepository.save(movieSaved);

        assertThat(updatedMovie).isNotNull();
        assertThat(updatedMovie.getTitle()).isNotNull();
        assertThat(updatedMovie.getCategory()).isNotNull();
    }

    @Test
    public void MovieRepository_MovieDelete_returnMovieIsEmpty() {
        Movie movie = Movie.builder()
                .title("Goodfellas")
                .category(Category.builder().name("Drama").build())
                .summary("Henry grows up idolising mobsters in his impoverished neighbourhood. Things take" +
                        " a turn for the worse when he along with his friends Jimmy and Tommy decide to make" +
                        " their way up the mob hierarchy.")
                .releaseDate(1111)
                .director("Martin Scorsese")
                .actors(
                        new ArrayList<>(List.of(
                                Actor.builder().name("Ray Liotta").build(),
                                Actor.builder().name("Robert De Niro").build(),
                                Actor.builder().name("Joe Pesci").build()
                        ))
                )
                .reviews(new ArrayList<>())
                .build();

        movieRepository.save(movie);

        movieRepository.deleteById(movie.getId());
        Optional<Movie> movieReturn = movieRepository.findById(movie.getId());

        assertThat(movieReturn).isEmpty();
    }
}
