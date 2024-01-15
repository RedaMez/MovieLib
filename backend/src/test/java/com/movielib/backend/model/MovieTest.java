package com.movielib.backend.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

public class MovieTest extends Assertions {

    @ParameterizedTest
    @CsvSource({
            "fbezubfybe, ebziufbkp, vberuygjçà, bveiuer, 5435, fgyvbeurube, dazdzda",
            "bfzeiubeub, jfioerhfn, qé&aéqzdez, kpokkkk, 7567, nuitb,toprr, xfddxxf",
            "vbdirubjrn, kjgflvvpo, hbvk^p$rfr, njnjdvd, 2313, jiburojtçà§, fyuftff"
    })
    public void testMovie(String title, String url, String summary, String category, int releaseDate, String director, String actor){
        Movie m = Movie.builder()
                .title(title)
                .imageUrl(url)
                .category(
                        Category.builder().name(category).build()
                )
                .summary(summary)
                .releaseDate(releaseDate)
                .director(director)
                .actors(
                        List.of(Actor.builder().name(actor).build())
                )
                .reviews(new ArrayList<>())
                .build();
        assertEquals(title, m.getTitle());
        assertEquals(summary, m.getSummary());
        assertEquals(releaseDate, m.getReleaseDate());
        assertEquals(director, m.getDirector());
    }

    @ParameterizedTest
    @CsvSource({
            "fbezubfybe, ebziufbkp, vberuygjçà, bveiuer, 5435, fgyvbeurube, dazdzda",
            "bfzeiubeub, jfioerhfn, qé&aéqzdez, kpokkkk, 7567, nuitb,toprr, xfddxxf",
            "vbdirubjrn, kjgflvvpo, hbvk^p$rfr, njnjdvd, 2313, jiburojtçà§, fyuftff"
    })
    public void testEquals(String title, String url, String summary, String category, int releaseDate, String director, String actor){
        Movie m1 = Movie.builder()
                .title(title)
                .imageUrl(url)
                .category(
                        Category.builder().name(category).build()
                )
                .summary(summary)
                .releaseDate(releaseDate)
                .director(director)
                .actors(
                        List.of(Actor.builder().name(actor).build()))
                .reviews(new ArrayList<>())
                .build();

        Movie m2 = Movie.builder()
                .title(title)
                .category(
                        Category.builder().name(category).build())
                .summary(summary)
                .releaseDate(releaseDate)
                .director(director)
                .actors(
                        List.of(Actor.builder().name(actor).build()))
                .reviews(new ArrayList<>())
                .build();
        assertEquals(m1, m2);
    }

    @ParameterizedTest
    @CsvSource({
            "aaaaaa, uuuuuu, bbbbbb, zzzzzz, 00000, cccccc, zzzzzz, mmmmmm, dddddd, eeeeee, nnnnnn, 11111, ffffff, hhhhhh",
            "fbezub, hhiueh, vberuy, jiojji, 54353, fgyvbe, pokkko, bfzeiu, nvuieb, qé&aéq, bhzebh, 75672, nuitbt, febibu"
    })
    public void testNotEquals(String title1, String url1, String summary1, String category1, int releaseDate1, String director1, String actor1,
                              String title2, String url2, String summary2, String category2, int releaseDate2, String director2, String actor2){
        assertNotEquals(Movie.builder()
                        .title(title1)
                        .category(
                                Category.builder().name(category1).build())
                        .summary(summary1)
                        .releaseDate(releaseDate1)
                        .director(director1)
                        .actors(
                                List.of(Actor.builder().name(actor1).build()))
                        .reviews(new ArrayList<>())
                        .build(),
                Movie.builder()
                        .title(title2)
                        .category(
                                Category.builder().name(category2).build())
                        .summary(summary2)
                        .releaseDate(releaseDate2)
                        .director(director2)
                        .actors(
                                List.of(Actor.builder().name(actor2).build()))
                        .reviews(new ArrayList<>())
                        .build());
    }

    @Test
    public void testAddReview(){
        Movie m = Movie.builder()
                .title("tttt")
                .imageUrl("uuuu")
                .summary("ssss")
                .category(Category.builder().name("cccc").build())
                .releaseDate(1111)
                .director("dddd")
                .actors(List.of(Actor.builder().name("aaaa").build()))
                .reviews(new ArrayList<>())
                .build();

        UserEntity u = UserEntity.builder()
                .username("uuuuuu")
                .email("eeeeeee")
                .password("ppppppp")
                .build();

        Review r = Review.builder()
                .userEntity(u)
                .movie(m)
                .rating(5.8)
                .comment("cccccc")
                .build();

        m.addReview(r);

        assertNotNull(m.getReviews());
        assertEquals(m.getReviews().get(0), r);
    }

    @Test
    public void testUpdateRating(){
        Movie m = Movie.builder()
                .title("tttt")
                .imageUrl("uuuu")
                .summary("ssss")
                .category(Category.builder().name("cccc").build())
                .releaseDate(1111)
                .director("dddd")
                .actors(List.of(Actor.builder().name("aaaa").build()))
                .reviews(new ArrayList<>())
                .build();

        UserEntity u = UserEntity.builder()
                .username("uuuuuu")
                .email("eeeeeee")
                .password("ppppppp")
                .build();

        Review r = Review.builder()
                .userEntity(u)
                .movie(m)
                .rating(5.8)
                .comment("cccccc")
                .build();

        m.addReview(r);

        assertEquals(m.getRating(), r.getRating());
    }

}
