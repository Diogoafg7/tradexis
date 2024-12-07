package com.example.trading_webapp_backend.service;

import com.example.trading_webapp_backend.dtos.WalletDTO;
import com.example.trading_webapp_backend.model.Wallet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WalletService {
    List<Wallet> getAllWallets();
    Wallet getWalletById(int id);
    Wallet getWalletByUsername(String username);
    Wallet addFundsToWallet(int id, double amount);
    Wallet createWallet(WalletDTO dto);
    void deleteWallet(int id);
}
