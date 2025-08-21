package com.example.dream_stadium.owner.match.entity;

import com.example.dream_stadium.common.user.entity.BaseEntity;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.owner.stadium.entity.Stadium;
import com.example.dream_stadium.owner.team.entity.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "cost", nullable = false, unique = true)
    private Long cost;

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

    public static Match toEntity(String name, Long cost, LocalDate matchDate, Team homeTeam, Team awayTeam, Stadium stadium, User user) {
        Match match = new Match();

        match.name = name;
        match.cost = cost;
        match.matchDate = matchDate;
        match.homeTeam = homeTeam;
        match.awayTeam = awayTeam;
        match.stadium = stadium;
        match.user = user;

        return match;
    }
}
