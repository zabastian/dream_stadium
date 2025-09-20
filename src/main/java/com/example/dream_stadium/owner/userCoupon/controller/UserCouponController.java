package com.example.dream_stadium.owner.userCoupon.controller;

import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium.owner.coupon.service.OwnerCouponService;
import com.example.dream_stadium.owner.userCoupon.dto.UserCouponRequestDto;
import com.example.dream_stadium.owner.userCoupon.dto.UserCouponResponseDto;
import com.example.dream_stadium.owner.userCoupon.service.UserCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class UserCouponController {

    private final UserCouponService userCouponService;

    @PostMapping("/createdUserCoupon")
    public ResponseEntity<List<UserCouponResponseDto>> createdUserCoupon(
            @RequestBody UserCouponRequestDto userCouponRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userCouponService.createUserCoupon(userCouponRequestDto));
    }

    @GetMapping("/listUserCoupon")
    public ResponseEntity<List<UserCouponResponseDto>> listUserCoupon() {
        return ResponseEntity.ok(userCouponService.userCouponList());
    }

    @DeleteMapping("/deletedUserCoupon/{userCouponId}")
    public ResponseEntity<Void> deletedUserCoupon(
            @PathVariable Long userCouponId
    ) {
        userCouponService.deleteUserCoupon(userCouponId);
        return ResponseEntity.noContent().build();
    }
}
