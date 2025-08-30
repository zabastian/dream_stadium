package com.example.dream_stadium.owner.team.entity;

import com.example.dream_stadium.common.user.entity.BaseEntity;
import com.example.dream_stadium.common.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "team_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public Team(String name, User user) {
        this.name = name;
        this.user = user;
    }


}
