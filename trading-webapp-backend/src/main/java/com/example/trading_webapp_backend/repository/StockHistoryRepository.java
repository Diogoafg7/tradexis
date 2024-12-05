package com.example.trading_webapp_backend.repository;

import com.example.trading_webapp_backend.model.Assets;
import com.example.trading_webapp_backend.model.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockHistoryRepository extends JpaRepository<StockHistory, Integer> {
    Optional<StockHistory> findByAsset_Id(int assetId);

    List<StockHistory> findAllByAsset_Id(int assetId);
}
