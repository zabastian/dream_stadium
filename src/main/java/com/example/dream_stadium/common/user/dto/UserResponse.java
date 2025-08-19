package com.example.dream_stadium.common.user.dto;

import com.example.dream_stadium.common.user.entity.UserRole;
import lombok.Getter;

@Getter
public class UserResponse {
    private String nickname;
    private UserRole userRole;

    public UserResponse(String nickname, UserRole userRole) {
        this.nickname = nickname;
        this.userRole = userRole;
    }
}
