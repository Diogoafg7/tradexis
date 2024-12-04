package com.example.trading_webapp_backend.service.impl;

import com.example.trading_webapp_backend.exception.CustomExceptions;
import com.example.trading_webapp_backend.model.User;
import com.example.trading_webapp_backend.model.Wallet;
import com.example.trading_webapp_backend.repository.UserRepository;
import com.example.trading_webapp_backend.repository.WalletRepository;
import com.example.trading_webapp_backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet getWalletById(int id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.ItemsNotFoundException("Wallet not found with id " + id));
    }

    /*@Override
    public Wallet getBalanceById(int id) {
        return getWalletById(id); // Assuming the balance is within the wallet object
    }

    @Override
    public Wallet getBalanceByUserId(int userId) {
        // Assuming Wallet has a userId property
        return wallets.stream()
                .filter(wallet -> wallet.getUser().getId() == userId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Wallet createWallet(Wallet wallet) {
        wallets.add(wallet);
        return wallet;
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
        wallets.removeIf(wallet -> wallet.getId() == id);
    }*/
}