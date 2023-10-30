package com.movielib.backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CategoryTest extends Assertions {

    @ParameterizedTest
    @CsvSource({"bdabzu", "&eé^ple&^", "eéu&ed", ",mfémù;z:^`m$"})
    public void testCategory(String name){
        Category c = new Category(name);
        assertEquals(name, c.getName());
    }

    @ParameterizedTest
    @CsvSource({"bdabzu", "&eé^ple&^", "eéu&ed", ",mfémù;z:^`m$"})
    public void testEquals(String name){
        Category c1 = new Category(name);
        Category c2 = new Category(name);
        assertEquals(c1, c2);
    }

    @ParameterizedTest
    @CsvSource({"AAA, BBB", "fuh=lm:ù, jeifrçà)"})
    public void testNotEquals(String name1, String name2){
        assertNotEquals(new Category(name1), new Category(name2));
    }
}
