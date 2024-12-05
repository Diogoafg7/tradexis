package com.example.trading_webapp_backend.controller;

import com.example.trading_webapp_backend.model.Assets;
import com.example.trading_webapp_backend.model.StockHistory;
import com.example.trading_webapp_backend.service.AssetsService;
import com.example.trading_webapp_backend.service.StockHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stock-history")
public class StockHistoryController {

    @Autowired
    private StockHistoryService stockHistoryService;

    @Autowired
    private AssetsService assetsService;

    // Endpoint para obter um histórico de preço por id
    @GetMapping("/get/{id}")
    public StockHistory getStockHistoryById(@PathVariable int id) {
        return stockHistoryService.getStockHistoryById(id);
    }

    // Endpoint para obter todos os históricos de preço
    @GetMapping("/get-all")
    public List<StockHistory> getAllStockHistories() {
        return stockHistoryService.getAllStockHistories();
    }

    // Endpoint para obter um histórico de preço por id do ativo
    @GetMapping("/get-by-asset-id/{assetId}")
    public List<StockHistory> getStockHistoriesByAssetId(@PathVariable int assetId) {
        return stockHistoryService.getStockHistoriesByAssetId(assetId);
    }

    // Endpoint para adicionar ou update um novo histórico de preço para um ativo
    @PostMapping("/add")
    public void addStockHistory(@RequestParam String symbol, @RequestParam double price) {
        Assets asset = assetsService.getAssetBySymbol(symbol);
        if (asset != null) {
            stockHistoryService.saveOrUpdateStockHistory(asset.getId(), price);
        }
    }

    // Endpoint para adicionar ou update um novo histórico de preço para um ativo com timestamp
    @PostMapping("/add-with-timestamp")
    public void addStockHistoryWithTimestamp(@RequestParam String symbol, @RequestParam double price, @RequestParam LocalDateTime timestamp) {
        Assets asset = assetsService.getAssetBySymbol(symbol);
        if (asset != null) {
            stockHistoryService.saveOrUpdateStockHistory(asset.getId(), price, timestamp);
        }
    }

    // Endpoint para deletar um histórico de preço por id
    @DeleteMapping("/delete/{id}")
    public void deleteStockHistory(@PathVariable int id) {
        stockHistoryService.deleteStockHistory(id);
    }

    // Endpoint para deletar um histórico de preço por id do ativo
    @DeleteMapping("/delete-by-asset-id/{assetId}")
    public void deleteStockHistoryByAssetId(@PathVariable int assetId) {
        stockHistoryService.deleteStockHistoryByAssetId(assetId);
    }

    // Endpoint para deletar todos os históricos de preço
    @DeleteMapping("/delete-all")
    public void deleteAllStockHistories() {
        stockHistoryService.deleteAllStockHistories();
    }
}
