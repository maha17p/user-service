package com.ms.user_service.controller;

import com.ms.user_service.dto.request.LoginReqeust;
import com.ms.user_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginReqeust loginReqeust){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReqeust.getUsername(),loginReqeust.getPassword()));
        return jwtUtil.generateAccessToken(loginReqeust.getUsername());
    }
}
