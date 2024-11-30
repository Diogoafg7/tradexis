package com.example.trading_webapp_backend.service;

import com.example.trading_webapp_backend.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService {
    List<User> getAllUsers();
    List<User> getAllAdmins();
    User getUserById(int id);
    User getAdminById(int id);
    User createUser(User user);
    User updateUser(int id, User userDetails);
    void deleteUser(int id);
    User updatePassword(int id, String password, String oldPassword);
}