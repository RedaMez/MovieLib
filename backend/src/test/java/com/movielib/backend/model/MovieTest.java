package com.movielib.backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MovieTest extends Assertions {

    @ParameterizedTest
    @CsvSource({
            "fbezubfybe, vberuygjçà, 5435, fgyvbeurube",
            "bfzeiubeub, qé&aéqzdez, 7567, nuitb,toprr",
            "vbdirubjrn, hbvk^p$rfr, 2313, jiburojtçà§"
    })
    public void testMovie(String title, String summary, int releaseDate, String director){
        Movie m = new Movie(title, null, summary, releaseDate, director, null);
        assertEquals(title, m.getTitle());
        assertEquals(summary, m.getSummary());
        assertEquals(releaseDate, m.getReleaseDate());
        assertEquals(director, m.getDirector());
    }

    @ParameterizedTest
    @CsvSource({
            "fbezubfybe, vberuygjçà, 5435, fgyvbeurube",
            "bfzeiubeub, qé&aéqzdez, 7567, nuitb,toprr",
            "vbdirubjrn, hbvk^p$rfr, 2313, jiburojtçà§"
    })
    public void testEquals(String title, String summary, int releaseDate, String director){
        Movie m1 = new Movie(title, null, summary, releaseDate, director, null);
        Movie m2 = new Movie(title, null, summary, releaseDate, director, null);
        assertEquals(m1, m2);
    }

    @ParameterizedTest
    @CsvSource({
            "aaaaaa, bbbbbb, 00000, cccccc, dddddd, eeeeee, 11111, ffffff",
            "fbezubfybe, vberuygjçà, 5435, fgyvbeurube, bfzeiubeub, qé&aéqzdez, 7567, nuitb,toprr"
    })
    public void testNotEquals(String title1, String summary1, int releaseDate1, String director1, String title2, String summary2, int releaseDate2, String director2){
        assertNotEquals(new Movie(title1, null, summary1, releaseDate1, director1, null),
                        new Movie(title2, null, summary2, releaseDate2, director2, null));
    }

}
