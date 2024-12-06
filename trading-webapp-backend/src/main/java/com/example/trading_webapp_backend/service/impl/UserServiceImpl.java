package com.example.trading_webapp_backend.service.impl;

import com.example.trading_webapp_backend.model.User;
import com.example.trading_webapp_backend.model.Wallet;
import com.example.trading_webapp_backend.repository.UserRepository;
import com.example.trading_webapp_backend.service.PortfolioService;
import com.example.trading_webapp_backend.service.UserService;
import com.example.trading_webapp_backend.exception.CustomExceptions.UserCreationException;
import com.example.trading_webapp_backend.exception.CustomExceptions.UserNotFoundException;
import com.example.trading_webapp_backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WalletService walletService;

    @Autowired
    private PortfolioService portfolioService;

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
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserCreationException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        // Criar carteira e portfólio
        walletService.createWallet(savedUser.getId(), 0.0);
        portfolioService.createPortfolio(savedUser.getUsername());
        return savedUser;
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
        Optional<User> user = userRepository.findByUsername(username);
        user.ifPresent(value -> {
            value.setUsername(userDetails.getUsername());
            value.setEmail(userDetails.getEmail());
            value.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            userRepository.save(value);
        });
        return user.orElseThrow(() -> new UserNotFoundException("User not found with username " + username));
    }

    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        walletService.deleteWallet(user.getId());
        portfolioService.deletePortfolio(user.getUsername());
        userRepository.deleteById(id);
    }

    @Override
    public User updatePassword(int id, String password, String oldPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}