package com.example.dream_stadium.customer.reservation.entity;

import com.example.dream_stadium.owner.userCoupon.entity.UserCoupon;
import jakarta.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id",unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn()
    private UserCoupon userCoupon;

}
