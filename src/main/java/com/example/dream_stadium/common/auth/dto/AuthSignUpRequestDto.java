package com.example.dream_stadium.common.auth.dto;

import com.example.dream_stadium.common.user.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AuthSignUpRequestDto {
    @NotBlank(message = "비면 안됩니다.")
    private String email;

    @NotBlank(message = "비면 안됩니다.")
    private String password;

    @NotBlank(message = "비면 안됩니다.")
    private String nickname;

    @NotNull(message = "비면 안됩니다.")
    private UserRole userRole;
}
