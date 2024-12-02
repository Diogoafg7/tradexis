package com.example.trading_webapp_backend.service.implementation;

import com.example.trading_webapp_backend.model.User;
import com.example.trading_webapp_backend.repository.UserRepository;
import com.example.trading_webapp_backend.service.UserService;
import com.example.trading_webapp_backend.exception.CustomExceptions.UserCreationException;
import com.example.trading_webapp_backend.exception.CustomExceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(User user) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        if (userOptional.isPresent()) {
            throw new UserCreationException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUserByUsername(String username, User userDetails) {
        User user = userRepository.findByUsername(username);
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updatePassword(int id, String password, String oldPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new UserNotFoundException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

}