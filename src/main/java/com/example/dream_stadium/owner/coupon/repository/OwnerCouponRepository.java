package com.example.dream_stadium.owner.coupon.repository;

import com.example.dream_stadium.owner.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerCouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findByUserId(Long userId);
}
