package com.example.trading_webapp_backend.service.impl;

import com.example.trading_webapp_backend.exception.CustomExceptions;
import com.example.trading_webapp_backend.model.User;
import com.example.trading_webapp_backend.model.Wallet;
import com.example.trading_webapp_backend.repository.UserRepository;
import com.example.trading_webapp_backend.repository.WalletRepository;
import com.example.trading_webapp_backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet getWalletById(int id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.ItemsNotFoundException("Wallet not found with id " + id));
    }

    @Override
    public Wallet getBalanceById(int id) {
        return getWalletById(id); // Assuming the balance is within the wallet object
    }

    @Override
    public Wallet getBalanceByUserId(int userId) {
        // Assuming Wallet has a userId property
        return walletRepository.findByUser_Id(userId)
                .orElseThrow(() -> new CustomExceptions.ItemsNotFoundException("Wallet not found with user id " + userId));
    }

    @Override
    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet updateWalletById(int id, Wallet wallet) {
        Wallet existingWallet = getWalletById(id);
        if (existingWallet != null) {
            existingWallet.setBalance(wallet.getBalance());
            // Add other fields to update as necessary
            return existingWallet;
        }
        return null;
    }

    @Override
    public void deleteWallet(int id) {
        walletRepository.deleteById(id);
    }

    @Override
    public Wallet getWalletByUserId(int userId) {
        return walletRepository.findByUser_Id(userId)
                .orElseThrow(() -> new CustomExceptions.ItemsNotFoundException("Wallet not found with user id " + userId));
    }

    @Override
    public Wallet createWallet(int userId, double balance) {
        Wallet wallet = new Wallet();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptions.ItemsNotFoundException("User not found with id " + userId));
        wallet.setUser(user);
        wallet.setBalance(balance);
        return walletRepository.save(wallet);
    }

    @Transactional
    public Wallet updateWalletBalance(int userId, double balance) {
        Wallet wallet = getWalletByUserId(userId);
        wallet.setBalance(balance);
        return walletRepository.save(wallet);
    }


    @Override
    public void deleteWalletByUserId(int userId) {
        Wallet wallet = getWalletByUserId(userId);
        walletRepository.delete(wallet);
    }

    @Override
    public void deleteAllWallets() {
        walletRepository.deleteAll();
    }
}