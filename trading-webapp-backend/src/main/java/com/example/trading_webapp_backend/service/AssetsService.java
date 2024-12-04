package com.example.trading_webapp_backend.service;

import com.example.trading_webapp_backend.model.Assets;

import java.time.LocalDateTime;
import java.util.List;

public interface AssetsService {
    List<Assets> getAllAssets();
    Assets getAssetById(int id);
    Assets getAssetBySymbol(String symbol);
    void updateAssetPrice(int id, double price);
    void updateAssetsPricesFromApi();
    String getAssetPrice(String symbol);
    void importAssetsFromFile();
    void saveOrUpdateAsset(String symbol, String name, Double price, Long typeId, LocalDateTime createdAt);
    void deleteAllAssets();
}
