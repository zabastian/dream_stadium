package com.example.dream_stadium.owner.userCoupon.repository;

import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.owner.coupon.entity.Coupon;
import com.example.dream_stadium.owner.userCoupon.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
    List<UserCoupon> findByUserAndCoupon(User user, Coupon coupon);
    List<UserCoupon> findByUserAndIsdownloadTrue(User user);
}
