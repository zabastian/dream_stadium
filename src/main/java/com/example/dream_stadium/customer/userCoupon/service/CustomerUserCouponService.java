package com.example.dream_stadium.customer.userCoupon.service;

import com.example.dream_stadium.common.auth.repository.AuthRepository;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import com.example.dream_stadium.owner.coupon.repository.OwnerCouponRepository;
import com.example.dream_stadium.owner.userCoupon.dto.UserCouponResponseDto;
import com.example.dream_stadium.owner.userCoupon.entity.UserCoupon;
import com.example.dream_stadium.owner.userCoupon.repository.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerUserCouponService {

    private final AuthRepository authRepository;
    private final UserCouponRepository userCouponRepository;
    private final OwnerCouponRepository ownerCouponRepository;

    public List<UserCouponResponseDto> userCouponList(Long userId) {

        User user = authRepository.findById(userId)
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        return user.getUserCoupons()
                .stream()
                .map(UserCouponResponseDto::to)
                .toList();
    }

   /* public void userCouponDownload(Long userId, Long couponId) {
        User user = authRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        Coupon coupon = ownerCouponRepository.findById(couponId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        UserCoupon userCoupon = userCouponRepository.findByUserAndCoupon(user, coupon)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        // Optional → List로 변경 (DB에 동일 User+Coupon 여러 개 있어도 안전)
        List<UserCoupon> userCoupons = userCouponRepository.findByUserAndCoupon(user, coupon);

        UserCoupon userCoupon = userCoupons.stream()
                .filter(uc -> !uc.isIsdownload())
                .findFirst()
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        userCoupon.download(); //

        user.getUserCoupons().add(userCoupon);
        coupon.getUserCoupons().add(userCoupon);

        userCouponRepository.save(userCoupon);
    }*/

    public void userCouponDownload(Long userId, Long userCouponId) {
        User user = authRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        UserCoupon userCoupon = userCouponRepository.findById(userCouponId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if (!userCoupon.getUser().getId().equals(userId)) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }

        if (userCoupon.isIsdownload()) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }

        userCoupon.download();

        userCouponRepository.save(userCoupon);
    }

    public List<UserCouponResponseDto> userCouponDownloaded(Long userId) {

        User user = authRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        List<UserCoupon> userCoupons = userCouponRepository.findByUserAndIsdownloadTrue(user)
                .stream()
                .filter(n -> !n.isUsed())
                .toList();

        return userCoupons
                .stream()
                .map(UserCouponResponseDto::to)
                .toList();

    }
}
