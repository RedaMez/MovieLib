package com.movielib.backend.repository;

import com.movielib.backend.model.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserEntityRepositoryTest extends Assertions {

    UserRepository userRepository;

    @Autowired
    public UserEntityRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void ReviewRepository_SaveAll_ReturnsSavedReview(){
        UserEntity userEntity = UserEntity.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();

        UserEntity savedUserEntity = userRepository.save(userEntity);

        assertThat(savedUserEntity).isNotNull();
        assertThat(savedUserEntity.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepository_GetAll_ReturnMoreThanOneUser(){
        UserEntity userEntity = UserEntity.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();
        UserEntity userEntity2 = UserEntity.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();

        userRepository.save(userEntity);
        userRepository.save(userEntity2);

        List<UserEntity> userEntityList = userRepository.findAll();

        assertThat(userEntityList).isNotNull();
        assertThat(userEntityList.size()).isEqualTo(2);
    }

    @Test
    public void UserRepository_FindById_ReturnsSavedUser(){
        UserEntity userEntity = UserEntity.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();

        userRepository.save(userEntity);

        UserEntity userEntityReturn = userRepository.findById(userEntity.getId()).get();

        assertThat(userEntityReturn).isNotNull();
    }

    @Test
    public void UserRepository_UpdateUser_ReturnsUser(){
        UserEntity userEntity = UserEntity.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();

        userRepository.save(userEntity);

        UserEntity userEntitySave = userRepository.findById(userEntity.getId()).get();
        userEntitySave.setEmail("new_email");
        userEntitySave.setPassword("new_password");
        UserEntity updatedUserEntity = userRepository.save(userEntitySave);

        assertThat(updatedUserEntity.getEmail()).isNotNull();
        assertThat(updatedUserEntity.getPassword()).isNotNull();
    }

    @Test
    public void UserRepository_UserDelete_returnUserIsEmpty() {
        UserEntity userEntity = UserEntity.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();

        userRepository.save(userEntity);

        userRepository.deleteById(userEntity.getId());
        Optional<UserEntity> userReturn = userRepository.findById(userEntity.getId());

        assertThat(userReturn).isEmpty();
    }

}
