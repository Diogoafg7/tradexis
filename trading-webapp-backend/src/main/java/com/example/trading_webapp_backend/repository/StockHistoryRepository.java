package com.example.trading_webapp_backend.repository;

import com.example.trading_webapp_backend.model.Assets;
import com.example.trading_webapp_backend.model.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Integer> {
    Optional<StockHistory> findByAsset_Id(int assetId);
}
