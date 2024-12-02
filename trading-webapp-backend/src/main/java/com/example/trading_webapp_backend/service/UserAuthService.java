package com.example.trading_webapp_backend.service;

import com.example.trading_webapp_backend.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAuthService {
    UserDetailsService userDetailsService();
}