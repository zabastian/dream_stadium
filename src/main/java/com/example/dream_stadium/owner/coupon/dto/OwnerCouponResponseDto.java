package com.example.dream_stadium.owner.coupon.dto;

import com.example.dream_stadium.common.user.entity.UserRole;
import com.example.dream_stadium.owner.coupon.entity.Coupon;
import com.example.dream_stadium.owner.coupon.entity.CouponType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OwnerCouponResponseDto {

    private String name;
    private CouponType couponType;

    /*public OwnerCouponResponseDto(Coupon coupon) {
        this.name = coupon.getName();
        this.couponType = coupon.getCouponType();
    }*/

    public static OwnerCouponResponseDto to(Coupon coupon) {
        return new OwnerCouponResponseDto(coupon.getName(), coupon.getCouponType());
    }

}
