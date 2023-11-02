package com.movielib.backend.repository;

import com.movielib.backend.model.Review;
import com.movielib.backend.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest extends Assertions {

    UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void ReviewRepository_SaveAll_ReturnsSavedReview(){
        User user = User.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();

        User savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepository_GetAll_ReturnMoreThanOneUser(){
        User user = User.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();
        User user2 = User.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();

        userRepository.save(user);
        userRepository.save(user2);

        List<User> userList = userRepository.findAll();

        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(2);
    }

    @Test
    public void UserRepository_FindById_ReturnsSavedUser(){
        User user = User.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();

        userRepository.save(user);

        User userReturn = userRepository.findById(user.getId()).get();

        assertThat(userReturn).isNotNull();
    }

    @Test
    public void UserRepository_UpdateUser_ReturnsUser(){
        User user = User.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();

        userRepository.save(user);

        User userSave = userRepository.findById(user.getId()).get();
        userSave.setEmail("new_email");
        userSave.setPassword("new_password");
        User updatedUser = userRepository.save(userSave);

        assertThat(updatedUser.getEmail()).isNotNull();
        assertThat(updatedUser.getPassword()).isNotNull();
    }

    @Test
    public void UserRepository_UserDelete_returnUserIsEmpty() {
        User user = User.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();

        userRepository.save(user);

        userRepository.deleteById(user.getId());
        Optional<User> userReturn = userRepository.findById(user.getId());

        assertThat(userReturn).isEmpty();
    }

}
