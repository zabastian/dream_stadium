package com.example.dream_stadium.customer.reservation.controller;

import com.example.dream_stadium.customer.reservation.dto.ReservationRequestDto;
import com.example.dream_stadium.customer.reservation.dto.ReservationResponseDto;
import com.example.dream_stadium.customer.reservation.entity.Reservation;
import com.example.dream_stadium.customer.reservation.repository.ReservationRepository;
import com.example.dream_stadium.customer.reservation.service.ReservationService;
import com.example.dream_stadium.customer.userCoupon.service.CustomerUserCouponService;
import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;

    @PostMapping("/createdReservation")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ReservationResponseDto> createdReservation(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @Valid @RequestBody ReservationRequestDto reservationRequestDto
       ) {
        ReservationResponseDto reservationResponseDto = reservationService.createReservation(customUserPrincipal.getUserId(), reservationRequestDto);
        return ResponseEntity.ok(reservationResponseDto);

    }

    @GetMapping("/listReservation")
    public ResponseEntity<List<ReservationResponseDto>> listReservation(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal
    ) {
        List<ReservationResponseDto> reservationResponseDto = reservationService.reservationList(customUserPrincipal.getUserId());
        return ResponseEntity.ok(reservationResponseDto);
    }

    @DeleteMapping("/deletedReservation/{reservationId}")
    public ResponseEntity<Void> deletedReservation(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @PathVariable Long reservationId
    ) {
        reservationService.deleteReservation(customUserPrincipal.getUserId(), reservationId);
        return ResponseEntity.noContent().build();
    }
}
