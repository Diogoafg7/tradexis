package com.example.trading_webapp_backend.controller.auth;

import com.example.trading_webapp_backend.service.JwtService;
import com.example.trading_webapp_backend.service.UserAuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.example.trading_webapp_backend.dtos.request.SignUpRequest;
import com.example.trading_webapp_backend.dtos.request.SigninRequest;
import com.example.trading_webapp_backend.dtos.response.JwtAuthenticationResponse;
import com.example.trading_webapp_backend.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserAuthService userAuthService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@Valid @RequestBody SigninRequest request) {
        try{
            return ResponseEntity.ok(authenticationService.signin(request));
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestBody Map<String, String> requestBody) {
        try {
            String token = requestBody.get("token");
            String userEmail = jwtService.extractUserName(token);
            UserDetails userDetails = userAuthService.userDetailsService().loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(token, userDetails)) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}