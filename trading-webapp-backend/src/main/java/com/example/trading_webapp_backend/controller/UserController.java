package com.example.trading_webapp_backend.controller;

//import com.example.trading_webapp_backend.dtos.request.PasswordChangeRequest;

import com.example.trading_webapp_backend.model.User;
import com.example.trading_webapp_backend.service.UserService;
import com.example.trading_webapp_backend.exception.CustomExceptions.UserNotFoundException;
import com.example.trading_webapp_backend.exception.CustomExceptions.UserCreationException;
import com.example.trading_webapp_backend.exception.CustomExceptions.DataIntegrityViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(Math.toIntExact(id)));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (UserCreationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(Math.toIntExact(id), user));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(Math.toIntExact(id));
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(userService.getUserByUsername(authentication.getName()));
    }

    @PutMapping("/me")
    public ResponseEntity<User> updateCurrentUser(@Valid @RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            return ResponseEntity.ok(userService.updateUserByUsername(authentication.getName(), user));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    // @PutMapping("/me/password")
}
