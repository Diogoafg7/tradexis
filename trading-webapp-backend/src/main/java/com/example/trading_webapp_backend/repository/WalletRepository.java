package com.example.trading_webapp_backend.repository;

import com.example.trading_webapp_backend.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Optional<Wallet> findById(int id);

    @Query("SELECT w FROM Wallet w JOIN w.user u WHERE u.username = :username")
    Optional<Wallet> findByUsername(@Param("username") String username);


}
