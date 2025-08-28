package com.example.dream_stadium.owner.match.entity;

import com.example.dream_stadium.common.user.entity.BaseEntity;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.owner.match_seat.entity.MatchSeat;
import com.example.dream_stadium.owner.stadium.entity.Stadium;
import com.example.dream_stadium.owner.team.entity.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matches")
@Getter
public class Match extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

//    @Column(name = "cost", nullable = false)
//    private Long cost;

    @Column(name = "match_date", nullable = false, unique = false)
    private LocalDate matchDate;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL/*, orphanRemoval = true*/)
    private List<MatchSeat> matchSeats = new ArrayList<>();


    public static Match toEntity(String name, LocalDate matchDate, Team homeTeam, Team awayTeam, Stadium stadium, User user) {
//        Match match = new Match();
//
//        match.name = name;
//        match.matchDate = matchDate;
//        match.homeTeam = homeTeam;
//        match.awayTeam = awayTeam;
//        match.stadium = stadium;
//        match.user = user;
//
//        return match;
        return new Match(null, name, matchDate, homeTeam, awayTeam, stadium, user, new ArrayList<>());
    } //JPA가 내부적으로 값을 채우는 것과 동일한 역할을 내가 명시적으로 구현

}
