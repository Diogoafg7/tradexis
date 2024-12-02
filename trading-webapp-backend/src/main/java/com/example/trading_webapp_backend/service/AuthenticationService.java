package com.example.trading_webapp_backend.service;

import com.example.trading_webapp_backend.dtos.request.SignUpRequest;
import com.example.trading_webapp_backend.dtos.request.SigninRequest;
import com.example.trading_webapp_backend.dtos.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

}