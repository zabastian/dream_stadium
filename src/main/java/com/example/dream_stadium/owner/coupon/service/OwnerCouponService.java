package com.example.dream_stadium.owner.coupon.service;

import com.example.dream_stadium.common.auth.repository.AuthRepository;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import com.example.dream_stadium.owner.coupon.dto.OwnerCouponRequestDto;
import com.example.dream_stadium.owner.coupon.dto.OwnerCouponResponseDto;
import com.example.dream_stadium.owner.coupon.entity.Coupon;
import com.example.dream_stadium.owner.coupon.repository.OwnerCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerCouponService {

    private final AuthRepository authRepository;
    private final OwnerCouponRepository ownerCouponRepository;

    public void createCoupon(Long userId, OwnerCouponRequestDto ownerCouponRequestDto) {
        User user = authRepository.findById(userId)
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        Coupon coupon = Coupon.from(
                ownerCouponRequestDto.getName(),
                ownerCouponRequestDto.getCouponType(),
                user);

        ownerCouponRepository.save(coupon);

    }

    public List<OwnerCouponResponseDto> couponList(Long userId) {
        List<Coupon> getCoupon = ownerCouponRepository.findByUserId(userId);


        return getCoupon
                .stream()
                .map(OwnerCouponResponseDto::to)
                .toList();

    }

    public void deleteCoupon(Long userId, Long couponId) {
        User user = authRepository.findById(userId)
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        Coupon coupon = ownerCouponRepository.findById(couponId)
                .orElseThrow(()->new BaseException(ErrorCode.USER_NOT_FOUND));

        if(!user.getId().equals(coupon.getUser().getId())) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }

        ownerCouponRepository.delete(coupon);
    }
}
