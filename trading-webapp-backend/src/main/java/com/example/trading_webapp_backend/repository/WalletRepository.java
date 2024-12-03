package com.example.trading_webapp_backend.repository;

import com.example.trading_webapp_backend.model.User;
import com.example.trading_webapp_backend.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Optional<Wallet> findById(int id);
}
