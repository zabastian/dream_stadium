package com.example.dream_stadium.customer.reservation.dto;

import lombok.Getter;

@Getter
public class ReservationRequestDto {
    private String name;
    private Long cost;
    private Long matchSeatId;
    private Long UserCouponId;
}
