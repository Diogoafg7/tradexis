package com.example.trading_webapp_backend.repository;

import com.example.trading_webapp_backend.model.Type_Trades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Type_TradesRepository extends JpaRepository<Type_Trades, Integer> {
    Optional<Type_Trades> findByName(String name);
}
