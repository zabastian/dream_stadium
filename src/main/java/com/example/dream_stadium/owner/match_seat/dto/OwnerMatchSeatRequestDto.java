package com.example.dream_stadium.owner.match_seat.dto;

import com.example.dream_stadium.owner.match_seat.entity.MatchSeatRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OwnerMatchSeatRequestDto {
    @NotNull
    private Long capacity;
    @NotNull
    private MatchSeatRole matchSeatRole;
    @NotNull
    private Long seatId;
    @NotNull
    private Long matchId;
}

