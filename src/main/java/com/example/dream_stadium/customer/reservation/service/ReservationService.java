package com.example.dream_stadium.customer.reservation.service;

import com.example.dream_stadium.common.auth.repository.AuthRepository;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.customer.reservation.dto.ReservationRequestDto;
import com.example.dream_stadium.customer.reservation.dto.ReservationResponseDto;
import com.example.dream_stadium.customer.reservation.entity.Reservation;
import com.example.dream_stadium.customer.reservation.repository.ReservationRepository;
import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import com.example.dream_stadium.owner.match_seat.entity.MatchSeat;
import com.example.dream_stadium.owner.match_seat.entity.MatchSeatRole;
import com.example.dream_stadium.owner.match_seat.repository.OwnerMatchSeatRepository;
import com.example.dream_stadium.owner.userCoupon.entity.UserCoupon;
import com.example.dream_stadium.owner.userCoupon.repository.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final AuthRepository authRepository;
    private final OwnerMatchSeatRepository ownerMatchSeatRepository;
    private final UserCouponRepository userCouponRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public ReservationResponseDto createReservation(Long userId, ReservationRequestDto reservationRequestDto) {

        User user = authRepository.findById(userId)
                .orElseThrow(()->new BaseException(ErrorCode.USER_NOT_FOUND));


        MatchSeat matchSeat = ownerMatchSeatRepository.findById(reservationRequestDto.getMatchSeatId())
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        UserCoupon userCoupon = null;
        if (reservationRequestDto.getUserCouponId() != null) {
            userCoupon = userCouponRepository.findById(reservationRequestDto.getUserCouponId())
                    .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

            userCoupon.setUsed(true);
        }

        Reservation reservation = Reservation.from(reservationRequestDto.getName(), reservationRequestDto.getCost(), user, matchSeat, userCoupon);

        if (matchSeat.matchSeatRole == MatchSeatRole.A_CLASS) {
           long cost =  reservation.getCost() / 10 + reservation.getCost();
           reservation.setCost(cost);
        }

        reservationRepository.save(reservation);

        // 7. Response DTO 반환
        return ReservationResponseDto.to(reservation);

    }
}
