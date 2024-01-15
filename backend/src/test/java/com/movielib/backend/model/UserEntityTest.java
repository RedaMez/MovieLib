package com.movielib.backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UserEntityTest extends Assertions {

    @ParameterizedTest
    @CsvSource({"bdabzu, ezfoine, vnzoezi", "&eé^ple&^, kdlvs;:, jiàç)", "eéu&ed, xaqs<sx, pok^)à"})
    public void testUser(String username, String email, String password){
        UserEntity u = new UserEntity(username, email, password);
        assertEquals(username, u.getUsername());
        assertEquals(email, u.getEmail());
        assertEquals(password, u.getPassword());
    }

    @ParameterizedTest
    @CsvSource({"bdabzu, ezfoine, vnzoezi", "&eé^ple&^, kdlvs;:, jiàç)", "eéu&ed, xaqs<sx, pok^)à"})
    public void testEquals(String username, String email, String password){
        UserEntity u1 = new UserEntity(username, email, password);
        UserEntity u2 = new UserEntity(username, email, password);
        assertEquals(u1, u2);
    }

    @ParameterizedTest
    @CsvSource({"aaaa, bbbb, cccc, dddd, eeee, ffff", "bdabzu, ezfoine, vnzoezi, eéu&ed, xaqs<sx, pok^)à"})
    public void testNotEquals(String username1, String email1, String password1, String username2, String email2, String password2){
        assertNotEquals(new UserEntity(username1, email1, password1),
                        new UserEntity(username2, email2, password2));
    }
}
