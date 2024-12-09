package com.HealthBizz.Survey.service;

import com.HealthBizz.Survey.entity.User;
import com.HealthBizz.Survey.exception.UserNotFoundException;
import com.HealthBizz.Survey.reporsitory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UserNotFoundException {
        User user = userRepo.findByEmailId(emailId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmailId(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}

