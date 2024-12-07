package com.example.trading_webapp_backend.controller;

import com.example.trading_webapp_backend.model.Trades;
import com.example.trading_webapp_backend.service.TradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trades")
public class TradesController {

    @Autowired
    private TradesService tradesService;

    //getAllTrades
    @GetMapping("/list")
    public List<Trades> getAllTrades() {
        return tradesService.getAllTrades();
    }

    //getTradeById
    @GetMapping("/{id}")
    public Trades getTradeById(int id) {
        return tradesService.getTradeById(id);
    }

    //getTradesByUserId
    @GetMapping("/user/{userId}")
    public List<Trades> getTradesByUserId(int userId) {
        return tradesService.getTradesByUserId(userId);
    }

    //getTradesByAssetId
    @GetMapping("/asset/{assetId}")
    public List<Trades> getTradesByAssetId(int assetId) {
        return tradesService.getTradesByAssetId(assetId);
    }

    //getTradesByTradeTypeId
    @GetMapping("/type/{tradeTypeId}")
    public List<Trades> getTradesByTradeTypeId(int tradeTypeId) {
        return tradesService.getTradesByTradeTypeId(tradeTypeId);
    }

    //addTrade
    @PostMapping("/add")
    public Trades addTrade(int userId, int assetId, String tradeTypeName, double quantity) {
        return tradesService.addTradeWithDetails(userId, assetId, tradeTypeName, quantity);
    }

    //updateTrade
    @PutMapping("/update")
    public Trades updateTrade(Trades trade) {
        return tradesService.updateTrade(trade);
    }

    //deleteTrade
    @DeleteMapping("/delete/{id}")
    public void deleteTrade(@PathVariable int id) {
        tradesService.deleteTrade(id);
    }

    //deleteAllTrades
    @DeleteMapping("/delete-all")
    public void deleteAllTrades() {
        tradesService.deleteAllTrades();
    }

    //addTradeWithDetails
    @PostMapping("/add-details/{userId}/{assetId}/{tradeTypeName}/{quantity}")
    public Trades addTradeWithDetails(
            @PathVariable("userId") int userId,
            @PathVariable("assetId") int assetId,
            @PathVariable("tradeTypeName") String tradeTypeName,
            @PathVariable("quantity") double quantity) {
        return tradesService.addTradeWithDetails(userId, assetId, tradeTypeName, quantity);
    }


}
