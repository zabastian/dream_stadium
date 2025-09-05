package com.example.dream_stadium.owner.userCoupon.entity;

import com.example.dream_stadium.common.user.entity.BaseEntity;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.owner.coupon.entity.Coupon;
import com.example.dream_stadium.owner.userCoupon.dto.UserCouponRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userCoupon_id", unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_download")
    private boolean isdownload = false;

    @Column(name = "is_used")
    private boolean isUsed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    public UserCoupon(String name, User user, Coupon coupon) {
        this.name = name;
        this.user = user;
        this.coupon = coupon;
    }

    public UserCoupon(String name, Coupon coupon) {
        this.name = name;
        this.coupon = coupon;
    }

    public UserCoupon() {}

    public static UserCoupon from(UserCouponRequestDto userCouponRequestDto,User user, Coupon coupon) {
        return new UserCoupon(userCouponRequestDto.getName(), user, coupon);
    }

    public void download() {
        this.isdownload = true;
    }
}
