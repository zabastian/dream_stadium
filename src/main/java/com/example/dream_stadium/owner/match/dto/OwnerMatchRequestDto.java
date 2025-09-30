package com.example.dream_stadium.owner.match.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class OwnerMatchRequestDto {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Long cost;
    @NotNull
    private LocalDate matchDate;
    @NotNull
    private Long homeTeamId;
    @NotNull
    private Long awayTeamId;
    @NotNull
    private Long stadiumId;
//
//    public static void from() {
//        this.name = name;
//    }
}
