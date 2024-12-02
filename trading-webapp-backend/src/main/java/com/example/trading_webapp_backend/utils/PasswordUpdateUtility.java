package com.example.trading_webapp_backend.utils;

import com.example.trading_webapp_backend.model.User;
import com.example.trading_webapp_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PasswordUpdateUtility {

    private static final Logger logger = LoggerFactory.getLogger(PasswordUpdateUtility.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void updatePasswords() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (!user.getPassword().startsWith("$2a$") && !user.getPassword().startsWith("$2b$")) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
            }
        }
    }
}