package com.example.dream_stadium.customer.reservation.entity;

import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.owner.match_seat.entity.MatchSeat;
import com.example.dream_stadium.owner.userCoupon.entity.UserCoupon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id",unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "cost", nullable = false)
    private Long cost;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    //유저별 예약정보같은 정보가 필요 할 수 있으니 추가해준다.

    @ManyToOne
    @JoinColumn(name = "match_seats")
    private MatchSeat matchSeat;
    //좌석예약을 하면 matchSeat의 capacity를 감소 시켜야 한다 그래서 0이상이여야하는 로직 필요
    //유저쿠폰 사용시에는 is_used를 true로 바꿔 더이상 조회 안되게 하면 된다.

    @ManyToOne
    @JoinColumn(name = "user_coupons")
    private UserCoupon userCoupon;




   /* public Reservation(String name, MatchSeat matchSeat, UserCoupon userCoupon) {
        this.name = name;
        this.matchSeat = matchSeat;
        this.userCoupon = userCoupon;
    }



    public static Reservation from(String name, MatchSeat matchSeat, UserCoupon userCoupon) {
        return new Reservation(name, matchSeat,userCoupon);
    }
    */
   public static Reservation from(String name, Long cost, User user, MatchSeat matchSeat, UserCoupon userCoupon) {
       Reservation reservation = new Reservation();
       reservation.name = name;
       reservation.cost = cost;
       reservation.user = user;
       reservation.matchSeat = matchSeat;
       reservation.userCoupon = userCoupon;
       return reservation;
   }

}
