package com.example.dream_stadium.customer.userCoupon.repository;

import com.example.dream_stadium.owner.userCoupon.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerUserCouponRepository extends JpaRepository<UserCoupon, Long> {
}
