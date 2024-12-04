package com.example.trading_webapp_backend.service.impl;

import com.example.trading_webapp_backend.dtos.WalletDTO;
import com.example.trading_webapp_backend.exception.CustomExceptions;
import com.example.trading_webapp_backend.model.User;
import com.example.trading_webapp_backend.model.Wallet;
import com.example.trading_webapp_backend.repository.UserRepository;
import com.example.trading_webapp_backend.repository.WalletRepository;
import com.example.trading_webapp_backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public Wallet getWalletByUsername(String username) {
        return walletRepository.findByUsername(username)
                .orElseThrow(() -> new CustomExceptions.ItemsNotFoundException("Wallet not found with username " + username));
    }

    @Override
    public Wallet addFundsToWallet(int walletId, double amount) {
        // Verificar se o valor a ser adicionado é positivo
        if (amount <= 0) {
            throw new CustomExceptions.InvalidAmountException("Amount must be greater than zero");
        }

        // Procurar a wallet pelo id
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new CustomExceptions.ItemsNotFoundException("Wallet not found with id " + walletId));

        // Adicionar os fundos ao saldo existente
        wallet.setBalance(wallet.getBalance() + amount);

        // Salvar a wallet atualizada no banco
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet createWallet(WalletDTO dto) {
        // Verifica se o user existe
        User user = userRepository.findById(dto.getUser_id())
                .orElseThrow(() -> new CustomExceptions.UserNotFoundException("User not found"));

        // Criar a nova Wallet
        Wallet wallet = new Wallet();
        wallet.setId(dto.getId());
        wallet.setUser(user);
        wallet.setBalance(dto.getBalance());
        wallet.setUpdatedAt(LocalDateTime.now());

        return walletRepository.save(wallet);
    }

    @Override
    public void deleteWallet(int id) {
        walletRepository.deleteById(id);
    }

}