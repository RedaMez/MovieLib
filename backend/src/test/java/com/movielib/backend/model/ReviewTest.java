package com.movielib.backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ReviewTest extends Assertions {

    @ParameterizedTest
    @CsvSource({"uefguizfu, 453525, 5.8, bzeuifzfbebzfb", "6546, 546343, 10., feziuhfheu", "643375, 6754475, .0, bfeziuuibzfbeiz"})
    public void testReview(String username, long movie_id, double rating, String comment){
        Movie m = Movie.builder()
                .id(movie_id)
                .build();
        UserEntity u = UserEntity.builder()
                .username(username)
                .email("eeeeeee")
                .password("ppppppp")
                .build();
        Review r = Review.builder()
                .userEntity(u)
                .movie(m)
                .rating(rating)
                .comment(comment)
                .build();
        assertEquals(username, r.getUserEntity().getUsername());
        assertEquals(movie_id, r.getMovie().getId());
        assertEquals(rating, r.getRating());
        assertEquals(comment, r.getComment());
    }

    @ParameterizedTest
    @CsvSource({"uefguizfu, 453525, 5.8, bzeuifzfbebzfb", "6546, 546343, 10., feziuhfheu", "643375, 6754475, .0, bfeziuuibzfbeiz"})
    public void testEquals(String username, long movie_id, double rating, String comment){
        Movie m = Movie.builder()
                .id(movie_id)
                .build();
        UserEntity u = UserEntity.builder()
                .username(username)
                .email("eeeeeee")
                .password("ppppppp")
                .build();
        Review r1 = Review.builder()
                .userEntity(u)
                .movie(m)
                .rating(rating)
                .comment(comment)
                .build();
        Review r2 = Review.builder()
                .userEntity(u)
                .movie(m)
                .rating(rating)
                .comment(comment)
                .build();
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @CsvSource({"uuuuuu, 11111, 1., aaaaaaaa, ooooooo, 33333, 5., bbbbbbb","uefguizfu, 453525, 5.8, bzeuifzfbebzfb, ioerhheuh, 546343, 10., feziuhfheu"})
    public void testNotEquals(String username1, long movie_id1, double rating1, String comment1, String username2, long movie_id2, double rating2, String comment2){
        Movie m1 = Movie.builder()
                .id(movie_id1)
                .build();
        Movie m2 = Movie.builder()
                .id(movie_id2)
                .build();
        UserEntity u1 = UserEntity.builder()
                .username(username1)
                .email("eeeeeee")
                .password("ppppppp")
                .build();
        UserEntity u2 = UserEntity.builder()
                .username(username2)
                .email("eeeeeee")
                .password("ppppppp")
                .build();
        Review r1 = Review.builder()
                .userEntity(u1)
                .movie(m1)
                .rating(rating1)
                .comment(comment1)
                .build();
        Review r2 = Review.builder()
                .userEntity(u2)
                .movie(m2)
                .rating(rating2)
                .comment(comment2)
                .build();
        assertNotEquals(r1, r2);
    }
}
