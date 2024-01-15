package com.movielib.backend.mapper;

import com.movielib.backend.dto.UserEntityDTO;
import com.movielib.backend.model.UserEntity;

public class UserMapper {

    public static UserEntity mapToUser(UserEntityDTO userEntityDTO){
        return UserEntity.builder()
                .id(userEntityDTO.getId())
                .username(userEntityDTO.getUsername())
                .email(userEntityDTO.getEmail())
                .password(userEntityDTO.getPassword())
                .build();
    }

    public static UserEntityDTO mapToUserDTO(UserEntity userEntity){
        return UserEntityDTO.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }
}
