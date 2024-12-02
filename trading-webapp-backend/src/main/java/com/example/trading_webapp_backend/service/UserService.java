package com.example.trading_webapp_backend.service;

import com.example.trading_webapp_backend.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    Optional<User> getUserByUsername(String username);
    User createUser(User user);
    User updateUser(int id, User userDetails);
    User updateUserByUsername(String username, User userDetails);
    void deleteUser(int id);
    User updatePassword(int id, String password, String oldPassword);
}