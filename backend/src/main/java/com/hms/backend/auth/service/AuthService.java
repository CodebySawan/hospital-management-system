package com.hms.backend.auth.service;

import com.hms.backend.auth.dto.RegisterRequest;
import com.hms.backend.auth.dto.RegisterResponse;
import com.hms.backend.auth.entity.User;
import com.hms.backend.auth.repository.UserRepository;
import com.hms.backend.common.exception.ResourceAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest request) {

        // check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
           throw new ResourceAlreadyExistsException("Email already registered");
        }

        // create user object
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .createdAt(LocalDateTime.now())
                .build();

        // save user
        userRepository.save(user);

        // return response
        return RegisterResponse.builder()
                .message("User registered successfully")
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}