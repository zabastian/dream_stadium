package com.example.dream_stadium.owner.stadium.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OwnerStadiumRequestDto {
    @NotBlank
    private String name;
}
