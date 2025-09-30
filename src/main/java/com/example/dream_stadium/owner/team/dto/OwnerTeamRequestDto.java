package com.example.dream_stadium.owner.team.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerTeamRequestDto {
    @NotBlank
    private String name;
}
