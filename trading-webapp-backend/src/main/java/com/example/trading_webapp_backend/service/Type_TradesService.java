package com.example.trading_webapp_backend.service;

import com.example.trading_webapp_backend.model.Type_Trades;

import java.util.List;
import java.util.Optional;

public interface Type_TradesService {
    List<Type_Trades> getAllTypeTrades();
    Type_Trades getTypeTradesById(int id);
    Optional<Type_Trades> getTypeTradesByName(String name);
    void addTypeTrades(Type_Trades typeTrades);
    void updateTypeTrades(int id, String typeTrades);
    void deleteTypeTrades(int id);
    void addTypeTrades(String name);
    void populateTrades();
}
