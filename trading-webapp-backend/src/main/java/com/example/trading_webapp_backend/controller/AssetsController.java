package com.example.trading_webapp_backend.controller;

import com.example.trading_webapp_backend.model.Assets;
import com.example.trading_webapp_backend.service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetsController {

    @Autowired
    private AssetsService assetsService;

    //updateAssetsPrices - update prices from API every 20 minutes(1200000) or 1 minute(60000)
    @Scheduled(fixedRate = 1200000)
    @GetMapping("/updatePrices")
    public ResponseEntity<String> updateAssetPrices() {
        assetsService.updateAssetsPricesFromApi();
        return ResponseEntity.ok("Preços atualizados com sucesso!");
    }

    @GetMapping("/list")
    public List<Assets> getAllAssets() {
        return assetsService.getAllAssets();
    }

    //getAssetById
    @GetMapping("/{id}")
    public Assets getAssetById(@PathVariable int id) {
        return assetsService.getAssetById(id);
    }

    //getAssetBySymbol
    @GetMapping("/symbol/{symbol}")
    public Assets getAssetBySymbol(@PathVariable String symbol) {
        return assetsService.getAssetBySymbol(symbol);
    }


    //getAssetPrice
    @GetMapping("/price/{symbol}")
    public String getAssetPrice(@PathVariable String symbol) {
        return assetsService.getAssetPrice(symbol);
    }


    //import assets from file with stock symbols and names
    //importAssets
    @GetMapping("/import")
    public ResponseEntity<String> importAssets() {
        assetsService.importAssetsFromFile();
        return ResponseEntity.ok("Ativos importados com sucesso!");
    }

    @GetMapping("delete-all")
    public ResponseEntity<String> deleteAllAssets() {
        assetsService.deleteAllAssets();
        return ResponseEntity.ok("Ativos deletados com sucesso!");
    }
}
