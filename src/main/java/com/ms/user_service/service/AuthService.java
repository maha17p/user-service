package com.ms.user_service.service;

import com.ms.user_service.dto.request.RegisterRequest;
import com.ms.user_service.entity.Role;
import com.ms.user_service.entity.User;
import com.ms.user_service.exception.UserAlreadyExistsException;
import com.ms.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username already exist");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exist");
        }

        String hash = encoder.encode(request.getPassword());
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(hash)
                .roles(Set.of(Role.ROLE_USER))
                .enabled(true)
                .build();
        userRepository.save(user);

        return "User created successfully";
    }
}
