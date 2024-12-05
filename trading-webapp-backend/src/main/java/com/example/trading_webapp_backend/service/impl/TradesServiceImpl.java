package com.example.trading_webapp_backend.service.impl;

import com.example.trading_webapp_backend.model.*;
import com.example.trading_webapp_backend.repository.*;
import com.example.trading_webapp_backend.service.TradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TradesServiceImpl implements TradesService {

    @Autowired
    private TradesRepository tradesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssetsRepository assetRepository;

    @Autowired
    private Type_TradesRepository typeTradeRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<Trades> getAllTrades() {
        return tradesRepository.findAll();
    }

    @Override
    public Trades getTradeById(int id) {
        return tradesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Trade not found"));
    }

    @Override
    public Trades addTrade(Trades trade) {
        return tradesRepository.save(trade);
    }

    @Override
    public Trades updateTrade(Trades trade) {
        return tradesRepository.save(trade);
    }

    @Override
    public void deleteTrade(int id) {
        tradesRepository.deleteById(id);
    }

    @Override
    public void deleteAllTrades() {
        tradesRepository.deleteAll();
    }

    @Override
    public List<Trades> getTradesByUserId(int userId) {
        return tradesRepository.findAllByUserId(userId);
    }

    @Override
    public List<Trades> getTradesByAssetId(int assetId) {
        return tradesRepository.findAllByAssetId(assetId);
    }

    @Override
    public List<Trades> getTradesByTradeTypeId(int tradeTypeId) {
        return tradesRepository.findAllByTradeTypeId(tradeTypeId);
    }

    @Transactional
    public Trades addTradeWithDetails(int userId, int assetId, String tradeTypeName, double quantity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Assets asset = assetRepository.findById(assetId).orElseThrow(() -> new IllegalArgumentException("Asset not found"));
        Type_Trades tradeType = typeTradeRepository.findByName(tradeTypeName).orElseThrow(() -> new IllegalArgumentException("Trade type not found"));

        double total = quantity * asset.getPrice();

        Trades trade = Trades.builder()
                .user(user)
                .asset(asset)
                .tradeType(tradeType)
                .quantity(quantity)
                .total(total)
                .build();

        //Remove or Add Value from User Wallet
        if(tradeType.getName().equals("Buy")) {
            Wallet wallet = walletRepository.findByUser_Id(userId).orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
            if(wallet.getBalance() < total) {
                throw new IllegalArgumentException("Insufficient balance");
            }
            wallet.setBalance(wallet.getBalance() - total);
        } else if(tradeType.getName().equals("Sell")) {
            Wallet wallet = walletRepository.findByUser_Id(userId).orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
            wallet.setBalance(wallet.getBalance() + total);
        } else {
            throw new IllegalArgumentException("Invalid trade type");
        }

        return tradesRepository.save(trade);
    }
}
