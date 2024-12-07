package com.example.trading_webapp_backend.repository;

import com.example.trading_webapp_backend.model.Assets;
import com.example.trading_webapp_backend.model.Portfolio;
import com.example.trading_webapp_backend.model.Type_Trades;
import com.example.trading_webapp_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    List<Portfolio> findByUser(User user);
    Optional<Portfolio> findByUserAndAsset(User user, Assets asset);
}
