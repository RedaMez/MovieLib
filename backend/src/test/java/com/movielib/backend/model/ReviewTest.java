package com.movielib.backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ReviewTest extends Assertions {

    @ParameterizedTest
    @CsvSource({"013452, 453525, 5.8, bzeuifzfbebzfb", "6546, 546343, 10., feziuhfheu", "643375, 6754475, .0, bfeziuuibzfbeiz"})
    public void testReview(long user_id, long movie_id, double rating, String comment){
        Review r = new Review(user_id, movie_id, rating, comment);
        assertEquals(user_id, r.getUserId());
        assertEquals(movie_id, r.getMovieId());
        assertEquals(rating, r.getRating());
        assertEquals(comment, r.getComment());
    }

    @ParameterizedTest
    @CsvSource({"013452, 453525, 5.8, bzeuifzfbebzfb", "6546, 546343, 10., feziuhfheu", "643375, 6754475, .0, bfeziuuibzfbeiz"})
    public void testEquals(long user_id, long movie_id, double rating, String comment){
        Review r1 = new Review(user_id, movie_id, rating, comment);
        Review r2 = new Review(user_id, movie_id, rating, comment);
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @CsvSource({"00000, 11111, 1., aaaaaaaa, 222222, 33333, 5., bbbbbbb","013452, 453525, 5.8, bzeuifzfbebzfb, 6546, 546343, 10., feziuhfheu"})
    public void testNotEquals(long user_id1, long movie_id1, double rating1, String comment1, long user_id2, long movie_id2, double rating2, String comment2){
        assertNotEquals(new Review(user_id1, movie_id1, rating1, comment1),
                        new Review(user_id2, movie_id2, rating2, comment2));
    }
}
