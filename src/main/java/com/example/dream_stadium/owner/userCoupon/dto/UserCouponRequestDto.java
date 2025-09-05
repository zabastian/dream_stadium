package com.example.dream_stadium.owner.userCoupon.dto;

import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.owner.coupon.entity.Coupon;
import lombok.Getter;

@Getter
public class UserCouponRequestDto {
    private String name;
    private Long couponId;
}
