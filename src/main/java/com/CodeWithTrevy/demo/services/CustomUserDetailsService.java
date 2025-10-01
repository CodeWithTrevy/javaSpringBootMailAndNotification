package com.CodeWithTrevy.demo.services;

import com.CodeWithTrevy.demo.model.Users;
import com.CodeWithTrevy.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));

        return User.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .authorities(users.getRoles().split(","))
                .build();
    }
}
