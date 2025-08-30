package com.example.dream_stadium.owner.stadium.repository;

import com.example.dream_stadium.owner.stadium.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerStadiumRepository extends JpaRepository<Stadium, Long> {
}
