package com.example.trading_webapp_backend.service.impl;

import com.example.trading_webapp_backend.model.Assets;
import com.example.trading_webapp_backend.model.StockHistory;
import com.example.trading_webapp_backend.repository.StockHistoryRepository;
import com.example.trading_webapp_backend.service.StockHistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StockHistoryServiceImpl implements StockHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(AssetsServiceImpl.class);

    @Autowired
    private StockHistoryRepository stockHistoryRepository;

    @Override
    public StockHistory getStockHistoryById(int id) {
        return stockHistoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<StockHistory> getAllStockHistories() {
        return stockHistoryRepository.findAll();
    }

    @Override
    public void saveOrUpdateStockHistory(int assetId, double price) {
        StockHistory stockHistory = stockHistoryRepository.findByAsset_Id(assetId).orElse(null);
        if (stockHistory == null) {
            stockHistory = new StockHistory();
            stockHistory.setAssetId(assetId);
        }
        stockHistory.setPrice(price);
        stockHistoryRepository.save(stockHistory);
    }

    @Override
    public void saveOrUpdateStockHistory(int assetId, double price, LocalDateTime timestamp) {
        StockHistory stockHistory = stockHistoryRepository.findByAsset_Id(assetId).orElse(null);
        if (stockHistory == null) {
            stockHistory = new StockHistory();
            stockHistory.setAssetId(assetId);
        }
        stockHistory.setPrice(price);
        stockHistory.setTimestamp(timestamp);
        stockHistoryRepository.save(stockHistory);
    }

    @Override
    public void saveStockHistory(Assets asset, double price, LocalDateTime timestamp) {
        logger.info("Saving stock history for assetId: {}, price: {}, timestamp: {}", asset, price, timestamp);
        StockHistory stockHistory = new StockHistory();
        stockHistory.setAsset(asset);
        stockHistory.setPrice(price);
        stockHistory.setTimestamp(timestamp);
        stockHistoryRepository.save(stockHistory);
        logger.info("Stock history saved for assetId: {}, price: {}, timestamp: {}", asset, price, timestamp);
    }

    @Override
    public void deleteStockHistory(int id) {
        stockHistoryRepository.deleteById(id);
    }

    @Override
    public void deleteStockHistoryByAssetId(int assetId) {
        stockHistoryRepository.findByAsset_Id(assetId).ifPresent(stockHistoryRepository::delete);
    }

    @Override
    public void deleteAllStockHistories() {
        stockHistoryRepository.deleteAll();
    }

    // exportStockHistoriesToFile to folder resources/data
    @Override
    public void exportStockHistoriesToFile() {
        try {
            List<StockHistory> stockHistories = stockHistoryRepository.findAll();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("src/main/resources/data/stock_histories.json"), stockHistories);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void importStockHistoriesFromFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StockHistory[] stockHistories = objectMapper.readValue(new File("src/main/resources/data/stock_histories.json"), StockHistory[].class);
            for (StockHistory stockHistory : stockHistories) {
                stockHistoryRepository.save(stockHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StockHistory> getStockHistoriesByAssetId(int assetId) {
        return stockHistoryRepository.findAllByAsset_Id(assetId);
    }

}
