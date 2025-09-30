package com.example.dream_stadium.owner.seat.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OwnerSeatRequestDto {
    @NotNull
    private Long seatId;
}
