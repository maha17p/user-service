package com.ms.user_service.controller;

import com.ms.user_service.dto.request.LoginReqeust;
import com.ms.user_service.dto.request.RegisterRequest;
import com.ms.user_service.service.AuthService;
import com.ms.user_service.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register( @Valid @RequestBody RegisterRequest request){
        String response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
    }


    @PostMapping("/login")
    public String login(@RequestBody LoginReqeust loginReqeust){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReqeust.getUsername(),loginReqeust.getPassword()));
        return jwtUtil.generateAccessToken(loginReqeust.getUsername());
    }
}
