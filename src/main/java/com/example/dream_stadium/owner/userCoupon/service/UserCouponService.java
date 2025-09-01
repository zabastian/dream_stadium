package com.example.dream_stadium.owner.userCoupon.service;

import com.example.dream_stadium.common.auth.repository.AuthRepository;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import com.example.dream_stadium.owner.coupon.entity.Coupon;
import com.example.dream_stadium.owner.coupon.repository.OwnerCouponRepository;
import com.example.dream_stadium.owner.user.repository.OwnerRepository;
import com.example.dream_stadium.owner.userCoupon.dto.UserCouponRequestDto;
import com.example.dream_stadium.owner.userCoupon.dto.UserCouponResponseDto;
import com.example.dream_stadium.owner.userCoupon.entity.UserCoupon;
import com.example.dream_stadium.owner.userCoupon.repository.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserCouponService {

    private final AuthRepository authRepository;
    private final OwnerCouponRepository ownerCouponRepository;
    private final UserCouponRepository userCouponRepository;

    public UserCouponResponseDto createUserCoupon(UserCouponRequestDto userCouponRequestDto) {

        User user = authRepository.findById(userCouponRequestDto.getUserId())
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        Coupon coupon = ownerCouponRepository.findById(userCouponRequestDto.getCouponId())
                .orElseThrow(()->new BaseException(ErrorCode.USER_NOT_FOUND));

       UserCoupon userCoupon = UserCoupon.from(userCouponRequestDto, user, coupon);

       userCouponRepository.save(userCoupon);

        return UserCouponResponseDto.to(userCoupon);
    }

    public List<UserCouponResponseDto> userCouponList() {
        List<UserCoupon> userCoupons = userCouponRepository.findAll();
        return userCoupons
                .stream()
                .map(UserCouponResponseDto::to)
                .toList();
    }

  /*  public List<UserCouponResponseDto> userCouponListByUser(Long userId) {
        User user = authRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        return user.getUserCoupons()
                .stream()
                .map(UserCouponResponseDto::to)
                .toList();
    }*/

    public void deleteUserCoupon(Long userCouponId) {
        UserCoupon userCoupon = userCouponRepository.findById(userCouponId)
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        userCouponRepository.delete(userCoupon);
    }
}
