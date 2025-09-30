package com.example.dream_stadium.customer.user.controller;

import com.example.dream_stadium.customer.user.service.CustomerService;
import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @DeleteMapping("/userDelete")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> userDelete(@AuthenticationPrincipal CustomUserPrincipal userPrincipal) {
        customerService.getUserDelete(userPrincipal.getUserId());
        return ResponseEntity.noContent().build();
    }

}
