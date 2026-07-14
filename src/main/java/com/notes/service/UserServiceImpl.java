package com.notes.service;

import com.notes.dto.RegisterRequest;
import com.notes.entity.Users;
import com.notes.exception.UserAlreadyExistsException;
import com.notes.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    @Override
    public void register( RegisterRequest request) {
        if(repo.existsByUsername(request.username())){
            throw new UserAlreadyExistsException("Username Already Exists");
        }
        if(repo.existsByEmail(request.email())){
            throw new EntityExistsException("Email Already Exists");
        }
        Users users=Users.builder()
                .username(request.username())
                .email(request.email())
                .password(encoder.encode(request.password()))
                .build();

        repo.save(users);
    }
}
