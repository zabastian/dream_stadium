package com.example.dream_stadium.common.auth.dto;

import lombok.Getter;

@Getter
public class AuthLoginResponseDto {
    private final Long userId;
    private final String accessToken;
    private final String refreshToken;
    private final boolean deleted;

    public AuthLoginResponseDto(Long userId, String accessToken, String refreshToken, boolean deleted) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.deleted = deleted;
    }
}
