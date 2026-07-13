package com.notes.controller;

import com.notes.dto.AuthResponse;
import com.notes.dto.LoginRequest;
import com.notes.dto.RefreshRequest;
import com.notes.dto.RegisterRequest;
import com.notes.service.UserServiceImpl;
import com.notes.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceImpl service;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request){
        service.register(request);
        return "Registration Successful";
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        return new AuthResponse(jwtUtil.generateAccessToken(request.username()),
                jwtUtil.generateRefreshToken(request.username()));


    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@Valid @RequestBody RefreshRequest request){

        if(!jwtUtil.validateToken(request.refreshToken())){
            throw new RuntimeException("Inavalid Token");
        }

        String username= jwtUtil.extractUsername(request.refreshToken());

        return new AuthResponse(
                jwtUtil.generateAccessToken(username),
                request.refreshToken()
        );
    }

}
