package com.example.dream_stadium.owner.match_seat.dto;

import com.example.dream_stadium.owner.match_seat.entity.MatchSeat;
import com.example.dream_stadium.owner.match_seat.entity.MatchSeatRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

    @Getter
    @AllArgsConstructor
    public class OwnerMatchSeatResponseDto {
        private Long capacity;
        private MatchSeatRole matchSeatRole;
        private Long seatId;
        private Long matchId;

        public static OwnerMatchSeatResponseDto to(MatchSeat matchSeat) {
            return new OwnerMatchSeatResponseDto(
            matchSeat.getCapacity(),
            matchSeat.getMatchSeatRole(),
            matchSeat.getSeat().getId(),
            matchSeat.getMatch().getId()
            );
        }
    }

