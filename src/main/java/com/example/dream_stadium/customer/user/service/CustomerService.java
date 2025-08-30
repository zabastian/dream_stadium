package com.example.dream_stadium.customer.user.service;

import com.example.dream_stadium.common.auth.repository.AuthRepository;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final AuthRepository authRepository;

    @Transactional
    public void getUserDelete(Long userId) {

        User user = authRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

//        authRepository.delete(user); 하드 딜리트라 유저정보 나중에 조회해서 사용하기 위해서는 아래처럼 소프트 딜리트(delete가 true인것만 조회하면 추적 용이)가 필요

        user.setDeleted(true);
        authRepository.save(user);
    }
}
