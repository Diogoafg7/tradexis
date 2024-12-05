package com.example.trading_webapp_backend.service.impl;

import com.example.trading_webapp_backend.repository.Type_TradesRepository;
import com.example.trading_webapp_backend.model.Type_Trades;
import com.example.trading_webapp_backend.service.Type_TradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Type_TradesServiceImpl implements Type_TradesService {

    @Autowired
    private Type_TradesRepository typeTradesRepository;

    @Override
    public List<Type_Trades> getAllTypeTrades() {
        return typeTradesRepository.findAll();
    }

    @Override
    public Type_Trades getTypeTradesById(int id) {
        return typeTradesRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Type_Trades> getTypeTradesByName(String name) {
        return typeTradesRepository.findByName(name);
    }

    @Override
    public void addTypeTrades(Type_Trades typeTrades) {
        if (typeTradesRepository.findByName(typeTrades.getName()).isEmpty()) {
            typeTradesRepository.save(typeTrades);
        } else {
            System.err.println("Tipo de trade já existe: " + typeTrades.getName());
        }
    }

    @Override
    public void addTypeTrades(String name) {
        Type_Trades typeTrades = new Type_Trades();
        typeTrades.setName(name);
        addTypeTrades(typeTrades);
    }

    @Override
    public void updateTypeTrades(int id, String typeTrades) {
        Type_Trades typeTradesToUpdate = getTypeTradesById(id);
        if (typeTradesToUpdate != null) {
            typeTradesToUpdate.setName(typeTrades);
            typeTradesRepository.save(typeTradesToUpdate);
        } else {
            System.err.println("Tipo de trade com ID " + id + " não encontrado.");
        }
    }

    @Override
    public void deleteTypeTrades(int id) {
        Type_Trades typeTrades = getTypeTradesById(id);
        if (typeTrades != null) {
            typeTradesRepository.delete(typeTrades);
        } else {
            System.err.println("Tipo de trade com ID " + id + " não encontrado.");
        }
    }

    @Override
    public void populateTrades() {
        addTypeTrades("Buy");
        addTypeTrades("Sell");
    }
}
