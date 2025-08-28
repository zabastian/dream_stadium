package com.example.dream_stadium.owner.match_seat.dto;

import com.example.dream_stadium.owner.match_seat.entity.MatchSeatRole;
import lombok.Getter;

@Getter
public class OwnerMatchSeatRequestDto {
    private Long capacity;
    private MatchSeatRole matchSeatRole;
    private Long seatId;
    private Long matchId;
}

