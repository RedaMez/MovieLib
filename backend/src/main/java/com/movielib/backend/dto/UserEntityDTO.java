package com.movielib.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityDTO {
    private long id;
    @NotEmpty(message = "Username should not be empty")
    private String username;
    private String email;
    private String password;
    private List<String> roles;
}
