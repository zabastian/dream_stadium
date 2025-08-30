package com.example.dream_stadium.common.auth.controller;

import com.example.dream_stadium.common.auth.dto.AuthLoginRequestDto;
import com.example.dream_stadium.common.auth.dto.AuthLoginResponseDto;
import com.example.dream_stadium.common.auth.dto.AuthSignUpRequestDto;
import com.example.dream_stadium.common.auth.service.AuthService;
import com.example.dream_stadium.global.spring_security.TokenResponse;
import com.example.dream_stadium.global.spring_security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUpController(@Valid @RequestBody AuthSignUpRequestDto authRequestDto) {
        authService.signUpService(authRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponseDto> loginController(@Valid @RequestBody AuthLoginRequestDto authLoginRequestDto) {
        AuthLoginResponseDto authLoginResponseDto = authService.loginService(authLoginRequestDto);
        return ResponseEntity.ok(authLoginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody Map<String, String> request) {
//        String oldRefreshToken = request.get("refreshToken");
        return ResponseEntity.ok(tokenService.refreshTokens( request.get("refreshToken")));
    }
}
