package com.example.trading_webapp_backend.service;

import com.example.trading_webapp_backend.model.Trades;

import java.util.List;

public interface TradesService {
    List<Trades> getAllTrades();
    Trades getTradeById(int id);
    Trades addTrade(Trades trade);
    Trades updateTrade(Trades trade);
    void deleteTrade(int id);
    void deleteAllTrades();
    List<Trades> getTradesByUserId(int userId);
    List<Trades> getTradesByAssetId(int assetId);
    List<Trades> getTradesByTradeTypeId(int tradeTypeId);
    Trades addTradeWithDetails(int userId, int assetId, String tradeTypeName, double quantity);
    Trades updateTradeWithDetails(int id, int userId, int assetId, String tradeTypeName, double quantity);
}
