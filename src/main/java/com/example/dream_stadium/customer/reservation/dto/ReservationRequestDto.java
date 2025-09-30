package com.example.dream_stadium.customer.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReservationRequestDto {
    @NotBlank
    private String name;
    @NotNull
    private Long cost;
    @NotNull
    private Long matchSeatId;
    @NotNull
    private Long userCouponId;
}
