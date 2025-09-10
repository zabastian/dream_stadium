package com.example.dream_stadium.customer.reservation.dto;

import com.example.dream_stadium.customer.reservation.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ReservationResponseDto {
    private String name;
    private Long cost;
    private Long matchSeatId;
    private Long userCouponId;

    public static ReservationResponseDto to(Reservation reservation) {
        return new ReservationResponseDto(
                reservation.getName(),
                reservation.getCost(),
                reservation.getMatchSeat().getId(),
                reservation.getUserCoupon() != null ? reservation.getUserCoupon().getId() : null
        );
    }
}