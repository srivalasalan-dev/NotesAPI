package com.notes.service;

import com.notes.entity.Users;
import com.notes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Users user= repo.findByUsername(username)
               .orElseThrow(()-> new UsernameNotFoundException("Username Not Found!"));

       return User.builder()
               .username(user.getUsername())
               .password(user.getPassword())
               .build();

    }
}
