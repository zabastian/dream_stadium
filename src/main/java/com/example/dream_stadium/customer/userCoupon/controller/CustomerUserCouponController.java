package com.example.dream_stadium.customer.userCoupon.controller;

import com.example.dream_stadium.customer.userCoupon.dto.CustomerUserCouponResponseDto;
import com.example.dream_stadium.customer.userCoupon.service.CustomerUserCouponService;
import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
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
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerUserCouponController {

    private final CustomerUserCouponService customerUserCouponService;

    @GetMapping("/listUserCoupon") // 고객이 쿠폰확인 하는 기능
    public ResponseEntity<List<UserCouponResponseDto>> listUserCoupon(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal
    ) {
        return ResponseEntity.ok(customerUserCouponService.userCouponList(customUserPrincipal.getUserId()));
    }

    @PostMapping("/downloadUserCoupon/{couponId}") //고객이 쿠폰 확인한것을 다운로드 받는 기능(유저쿠폰다운로드 false -> true)
    public ResponseEntity<Void> downloadCoupon(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @PathVariable Long couponId
    ) {
        customerUserCouponService.userCouponDownload(customUserPrincipal.getUserId(), couponId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/downloadedUserCoupon")     //고객이 쿠폰 다운로드한것을 보관하는 기능
    public ResponseEntity<List<UserCouponResponseDto>> downloadedUserCoupon(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal
    ) {
        return ResponseEntity.ok(customerUserCouponService.userCouponDownloaded(customUserPrincipal.getUserId()));
    }

}

