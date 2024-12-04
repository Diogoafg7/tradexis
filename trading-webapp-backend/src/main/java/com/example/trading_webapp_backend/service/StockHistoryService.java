package com.example.trading_webapp_backend.service;

import com.example.trading_webapp_backend.model.Assets;
import com.example.trading_webapp_backend.model.StockHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface StockHistoryService {
    StockHistory getStockHistoryById(int id);
    List<StockHistory> getAllStockHistories();
    void saveOrUpdateStockHistory(int assetId, double price);
    void saveOrUpdateStockHistory(int assetId, double price, LocalDateTime timestamp);
    void saveStockHistory(Assets asset, double price, LocalDateTime timestamp);
    void deleteStockHistory(int id);
    void deleteStockHistoryByAssetId(int assetId);
    void deleteAllStockHistories();
    void importStockHistoriesFromFile();
    void exportStockHistoriesToFile();
}
