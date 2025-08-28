package com.example.dream_stadium.owner.seat.entity;

import com.example.dream_stadium.owner.match_seat.entity.MatchSeat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL/*, orphanRemoval = true*/)
    private List<MatchSeat> matchSeats = new ArrayList<>();

}
