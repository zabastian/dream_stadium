package com.example.dream_stadium.owner.match.repository;

import com.example.dream_stadium.owner.match.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerMatchRepository extends JpaRepository<Match, Long> {
}
