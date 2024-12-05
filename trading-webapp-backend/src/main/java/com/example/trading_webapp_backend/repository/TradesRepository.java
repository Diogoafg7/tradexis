package com.example.trading_webapp_backend.repository;

import com.example.trading_webapp_backend.model.Trades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradesRepository extends JpaRepository<Trades, Integer> {
    List<Trades> findAllByUserId(int userId);
    List<Trades> findAllByAssetId(int assetId);
    List<Trades> findAllByTradeTypeId(int tradeTypeId);
    List<Trades> findAllByUserIdAndAssetId(int userId, int assetId);
    List<Trades> findAllByUserIdAndTradeTypeId(int userId, int tradeTypeId);
    List<Trades> findAllByAssetIdAndTradeTypeId(int assetId, int tradeTypeId);
    List<Trades> findAllByUserIdAndAssetIdAndTradeTypeId(int userId, int assetId, int tradeTypeId);
}
