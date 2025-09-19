package com.example.dream_stadium.customer.reservation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationRequestDto {
    private String name;
    private Long cost;
    private Long matchSeatId;
    private Long userCouponId;
    private LocalDateTime alarmTime;
}
