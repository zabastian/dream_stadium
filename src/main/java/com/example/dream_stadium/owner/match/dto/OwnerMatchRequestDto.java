package com.example.dream_stadium.owner.match.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class OwnerMatchRequestDto {
    private Long id;
    private String name;
    private Long cost;
    private LocalDate matchDate;
    private Long homeTeamId;
    private Long awayTeamId;
    private Long stadiumId;
//
//    public static void from() {
//        this.name = name;
//    }
}
