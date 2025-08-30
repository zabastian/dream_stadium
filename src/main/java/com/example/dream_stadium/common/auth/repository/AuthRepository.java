package com.example.dream_stadium.common.auth.repository;

import com.example.dream_stadium.common.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.LongFunction;

    public interface AuthRepository extends JpaRepository<User, Long> {
        Optional<User> findByEmail(String email);

    }
