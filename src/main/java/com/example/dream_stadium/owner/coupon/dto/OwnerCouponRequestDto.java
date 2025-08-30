package com.example.dream_stadium.owner.coupon.dto;


import com.example.dream_stadium.owner.coupon.entity.Coupon;
import com.example.dream_stadium.owner.coupon.entity.CouponType;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

@Getter
public class OwnerCouponRequestDto {
    private String name;
    private CouponType couponType;

}
