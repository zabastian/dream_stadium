package com.example.dream_stadium.owner.match_seat.entity;

import com.example.dream_stadium.owner.match.entity.Match;
import com.example.dream_stadium.owner.seat.entity.Seat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class MatchSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matchSeat_Id", nullable = false)
    private Long id;

    @Column(name = "capacity", nullable = false)
    private Long capacity;

    @Enumerated(EnumType.STRING)
    public MatchSeatRole matchSeatRole;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    public Seat seat;

    @ManyToOne
    @JoinColumn(name = "match_id")
    public Match match;

    @Column(name = "is_reserved")
    private boolean isReserved = false;

    public MatchSeat(Long capacity, MatchSeatRole matchSeatRole, Seat seat, Match match) {
        this.capacity =capacity;
        this.matchSeatRole = matchSeatRole;
        this.seat = seat;
        this.match = match;
    }

    public static MatchSeat from(Long capacity, MatchSeatRole matchSeatRole, Seat seat, Match match) {
//        MatchSeat matchSeat = new MatchSeat();
//
//        matchSeat.capacity = capacity;
//        matchSeat.matchSeatRole = matchSeatRole;
//        matchSeat.seat = seat;
//        matchSeat.match = match;
//
//        return matchSeat;
        return new MatchSeat(capacity, matchSeatRole, seat, match);
    } //JPA가 내부적으로 값을 채우는 것과 동일한 역할을 내가 명시적으로 구현
}
