package com.example.dream_stadium.owner.team.dto;

import lombok.Getter;

@Getter
public class OwnerTeamResponseDto {
    private Long userId;
    private Long teamId;
    private String name;

    public OwnerTeamResponseDto(Long userId, Long teamId, String name) {
        this.userId = userId;
        this.teamId = teamId;
        this.name = name;
    }
}
