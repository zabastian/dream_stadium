package com.example.dream_stadium.owner.userCoupon.dto;

import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.owner.coupon.entity.Coupon;
import com.example.dream_stadium.owner.userCoupon.entity.UserCoupon;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCouponResponseDto {
    private Long id;
    private String name;
    private Long userId;
    private Long couponId;

    public static UserCouponResponseDto to(UserCoupon userCoupon) {
        return new UserCouponResponseDto (userCoupon.getId(), userCoupon.getName(), userCoupon.getUser().getId(), userCoupon.getCoupon().getId());
    }
}
