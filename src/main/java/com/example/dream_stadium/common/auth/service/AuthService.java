package com.example.dream_stadium.common.auth.service;

import com.example.dream_stadium.common.auth.PasswordEncoder;
import com.example.dream_stadium.common.auth.dto.AuthLoginRequestDto;
import com.example.dream_stadium.common.auth.dto.AuthLoginResponseDto;
import com.example.dream_stadium.common.auth.dto.AuthSignUpRequestDto;
import com.example.dream_stadium.common.auth.repository.AuthRepository;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import com.example.dream_stadium.global.spring_security.TokenResponse;
import com.example.dream_stadium.global.spring_security.TokenService;
import com.example.dream_stadium.global.spring_security.refresh_token.RefreshToken;
import com.example.dream_stadium.global.spring_security.refresh_token.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    public void signUpService(AuthSignUpRequestDto authRequestDto) {
        String encodedPassword = passwordEncoder.encode(authRequestDto.getPassword());
        User user = new User(authRequestDto.getEmail(), encodedPassword, authRequestDto.getNickname(),authRequestDto.getUserRole(), false);

        User savedUser = authRepository.save(user);

    }

    public AuthLoginResponseDto loginService(AuthLoginRequestDto authLoginRequestDto) {
        User user = authRepository.findByEmail(authLoginRequestDto.getEmail())
                .orElseThrow(() ->  new BaseException(ErrorCode.USER_NOT_FOUND));

        /*  if (PasswordEncoder.matches(authLoginRequestDto.getPassword(), user.getPassword())) {
            return new AuthLoginResponseDto(user.getId(), tokenService.createToken(user.getId(), user.getUserRole()) );
        }
        throw new BaseException(ErrorCode.USER_NOT_FOUND);*/
        // token이 accesstoken만 있다 가정하고 만료일도 지정 안했을때는 이렇게 사용해도 되지만 accesstoken,refreshtoken이 사용되는이상 아래와 같이 사용해야 한다.

        if (!PasswordEncoder.matches(authLoginRequestDto.getPassword(), user.getPassword())) {
            throw new BaseException(ErrorCode.LOGIN_FAILED);
        }

        String accessToken = tokenService.createAccessToken(user.getId(), user.getUserRole());
        String refreshToken = tokenService.createRefreshToken();

            if (refreshTokenRepository.findByUserId(user.getId()).isPresent() && !refreshTokenRepository.findByUserId(user.getId()).get().isExpired()) {
            refreshToken = refreshTokenRepository.findByUserId(user.getId()).get().getToken();
        }
        else {

            RefreshToken refreshTokenEntity = new RefreshToken(refreshToken, user.getId(), user.getUserRole(), Instant.now().plus(30, ChronoUnit.DAYS));
            // 30일 만료 기간 설정

            refreshTokenRepository.save(refreshTokenEntity);
        }

        return new AuthLoginResponseDto(user.getId(), accessToken, refreshToken, user.isDeleted());

       /* tokenEntity.setToken(newRefreshToken);
        tokenEntity.setExpiryDate(Instant.now().plus(30,ChronoUnit.DAYS));
        refreshTokenRepository.save(tokenEntity);

        return new TokenResponse(newAccessToken,newRefreshToken); 기존에 이와 같이 set문제가 발생
        */

    }
}
