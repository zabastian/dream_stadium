package com.example.dream_stadium.owner.coupon.entity;

import com.example.dream_stadium.common.user.entity.BaseEntity;
import com.example.dream_stadium.common.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_type", nullable = false)
    private CouponType couponType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Coupon(String name, CouponType couponType, User user) {
        this.name = name;
        this.couponType = couponType;
        this.user = user;
    }

    public static Coupon from(String name, CouponType couponType, User user) {
        return new Coupon(name, couponType, user);
    }

    public Coupon(String name, CouponType couponType) {
        this.name = name;
        this.couponType = couponType;
    }
}
