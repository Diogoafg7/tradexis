package com.example.trading_webapp_backend.service;

import com.example.trading_webapp_backend.model.Wallet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WalletService {
    List<Wallet> getAllWallets();
    Wallet getWalletById(int id);
    Wallet getBalanceById(int id);
    Wallet getBalanceByUserId(int user_id);
    Wallet createWallet(Wallet wallet);
    Wallet updateWalletById(int id, Wallet wallet);
    void deleteWallet(int id);
    Wallet getWalletByUserId(int userId);
    Wallet createWallet(int userId, double balance);
    Wallet updateWalletBalance(int userId, double balance);
    void deleteWalletByUserId(int userId);
    void deleteAllWallets();
}
