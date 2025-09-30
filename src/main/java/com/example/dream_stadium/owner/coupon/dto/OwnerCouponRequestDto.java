package com.example.dream_stadium.owner.coupon.dto;


import com.example.dream_stadium.owner.coupon.entity.Coupon;
import com.example.dream_stadium.owner.coupon.entity.CouponType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

@Getter
public class OwnerCouponRequestDto {
    @NotBlank
    private String name;
    @NotNull
    private CouponType couponType;

}
