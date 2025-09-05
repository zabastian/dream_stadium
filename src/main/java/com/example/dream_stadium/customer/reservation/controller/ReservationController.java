package com.example.dream_stadium.customer.reservation.controller;

import com.example.dream_stadium.customer.reservation.dto.ReservationRequestDto;
import com.example.dream_stadium.customer.reservation.dto.ReservationResponseDto;
import com.example.dream_stadium.customer.reservation.service.ReservationService;
import com.example.dream_stadium.customer.userCoupon.service.CustomerUserCouponService;
import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class ReservationController {

    private final ReservationService reservationService;

    public ResponseEntity<ReservationResponseDto> createdReservation(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @RequestBody ReservationRequestDto reservationRequestDto
       ) {
        ReservationResponseDto reservationResponseDto = reservationService.createReservation(customUserPrincipal.getUserId(), reservationRequestDto);
        return ResponseEntity.ok(reservationResponseDto);

    }
}
