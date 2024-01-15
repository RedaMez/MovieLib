package com.movielib.backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ActorTest extends Assertions {

    @ParameterizedTest
    @CsvSource({"bdabzu", "&eé^ple&^", "eéu&ed", ",mfémù;z:^`m$"})
    public void testActor(String name){
        Actor a = new Actor(name);
        assertEquals(name, a.getName());
    }

    @ParameterizedTest
    @CsvSource({"bdabzu", "&eé^ple&^", "eéu&ed", ",mfémù;z:^`m$"})
    public void testEquals(String name){
        Actor a1 = new Actor(name);
        Actor a2 = new Actor(name);
        assertEquals(a1, a2);
    }

    @ParameterizedTest
    @CsvSource({"AAA, BBB", "fuh=lm:ù, jeifrçà)"})
    public void testNotEquals(String name1, String name2){
        assertNotEquals(new Actor(name1), new Actor(name2));
    }
}
