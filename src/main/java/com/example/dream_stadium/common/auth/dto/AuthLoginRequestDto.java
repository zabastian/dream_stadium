package com.example.dream_stadium.common.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthLoginRequestDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
