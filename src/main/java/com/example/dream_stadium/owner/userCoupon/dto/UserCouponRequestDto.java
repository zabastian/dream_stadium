package com.example.dream_stadium.owner.userCoupon.dto;

import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.owner.coupon.entity.Coupon;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserCouponRequestDto {
    @NotBlank
    private String name;
    @NotNull
    private Long couponId;
}
