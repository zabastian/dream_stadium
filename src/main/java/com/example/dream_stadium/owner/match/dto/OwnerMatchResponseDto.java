package com.example.dream_stadium.owner.match.dto;

import com.example.dream_stadium.owner.match.entity.Match;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class OwnerMatchResponseDto {
    private Long matchId;
    private String name;
    private Long cost;
    private LocalDate matchDate;
    private Long homeTeamId;
    private Long awayTeamId;
    private Long stadiumId;

    public static OwnerMatchResponseDto from(Match match) {
        return new OwnerMatchResponseDto(
                match.getId(),
                match.getName(),
                match.getCost(),
                match.getMatchDate(),
                match.getHomeTeam().getId(),
                match.getAwayTeam().getId(),
                match.getStadium().getId()
        );

    }
}


