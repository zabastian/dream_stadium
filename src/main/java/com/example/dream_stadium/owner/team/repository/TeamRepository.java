package com.example.dream_stadium.owner.team.repository;

import com.example.dream_stadium.owner.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
//    List<Team> findAll();
    Optional<Team> findByName(String name);
}
