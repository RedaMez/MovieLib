package com.movielib.backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UserTest extends Assertions {

    @ParameterizedTest
    @CsvSource({"bdabzu, ezfoine, vnzoezi", "&eé^ple&^, kdlvs;:, jiàç)", "eéu&ed, xaqs<sx, pok^)à"})
    public void testUser(String username, String email, String password){
        User u = new User(username, email, password);
        assertEquals(username, u.getUsername());
        assertEquals(email, u.getEmail());
        assertEquals(password, u.getPassword());
    }

    @ParameterizedTest
    @CsvSource({"bdabzu, ezfoine, vnzoezi", "&eé^ple&^, kdlvs;:, jiàç)", "eéu&ed, xaqs<sx, pok^)à"})
    public void testEquals(String username, String email, String password){
        User u1 = new User(username, email, password);
        User u2 = new User(username, email, password);
        assertEquals(u1, u2);
    }

    @ParameterizedTest
    @CsvSource({"aaaa, bbbb, cccc, dddd, eeee, ffff", "bdabzu, ezfoine, vnzoezi, eéu&ed, xaqs<sx, pok^)à"})
    public void testNotEquals(String username1, String email1, String password1, String username2, String email2, String password2){
        assertNotEquals(new User(username1, email1, password1),
                        new User(username2, email2, password2));
    }
}
