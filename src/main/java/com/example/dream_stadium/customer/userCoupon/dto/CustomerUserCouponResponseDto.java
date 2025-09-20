package com.example.dream_stadium.customer.userCoupon.dto;

import com.example.dream_stadium.owner.userCoupon.dto.UserCouponResponseDto;
import com.example.dream_stadium.owner.userCoupon.entity.UserCoupon;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerUserCouponResponseDto {
    private Long id;
    private String name;
    private Long couponId;

    public static CustomerUserCouponResponseDto to(UserCoupon userCoupon) {
        return new CustomerUserCouponResponseDto(userCoupon.getId(), userCoupon.getName(), userCoupon.getCoupon().getId());
    }
}
