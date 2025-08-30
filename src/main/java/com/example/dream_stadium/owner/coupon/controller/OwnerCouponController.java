package com.example.dream_stadium.owner.coupon.controller;

import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium.owner.coupon.dto.OwnerCouponRequestDto;
import com.example.dream_stadium.owner.coupon.dto.OwnerCouponResponseDto;
import com.example.dream_stadium.owner.coupon.service.OwnerCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerCouponController {

    private final OwnerCouponService ownerCouponService;

    @PostMapping("/createCoupon")
    public ResponseEntity<Void> createdCoupon(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @RequestBody OwnerCouponRequestDto ownerCouponRequestDto
    ) {
        ownerCouponService.createCoupon(customUserPrincipal.getUserId(), ownerCouponRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/listCoupon")
    public ResponseEntity<List<OwnerCouponResponseDto>> listCoupon( @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal) {

        return ResponseEntity.ok(ownerCouponService.couponList(customUserPrincipal.getUserId()));
    }

    @DeleteMapping("/deleteCoupon/{couponId}")
    public ResponseEntity<Void> deletedCoupon(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @PathVariable Long couponId
    ) {
        ownerCouponService.deleteCoupon(customUserPrincipal.getUserId(), couponId);
        return ResponseEntity.noContent().build();
    }
}
