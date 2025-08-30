package com.example.dream_stadium.owner.userCoupon.entity;

import com.example.dream_stadium.common.user.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class UserCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userCoupon_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_used")
    private boolean isUsed = false;
}
