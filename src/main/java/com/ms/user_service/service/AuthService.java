package com.ms.user_service.service;

import com.ms.user_service.dto.request.RegisterRequest;
import com.ms.user_service.entity.User;
import com.ms.user_service.exception.UserAlreadyExistsException;
import com.ms.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username already exist");
        }

        if (userRepository.existyByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exist");
        }

        String hash = encoder.encode(request.getPassword());
        User.builder()
                .username(request.getUsername())
                .password(hash)
                .
                .build();

        return "User created successfully";
    }
}
