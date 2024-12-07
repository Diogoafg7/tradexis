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

import java.util.HashMap;
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
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest request) {
        try {
            authenticationService.signup(request); 

            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Signup successful! Please log in.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error during signup:", e);

            
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Signup failed: " + e.getMessage());
            return ResponseEntity.status(400).body(errorResponse);
        }
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
            String userName = jwtService.extractUserName(token);
            UserDetails userDetails = userAuthService.userDetailsService().loadUserByUsername(userName);
            if (jwtService.isTokenValid(token, userDetails)) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}