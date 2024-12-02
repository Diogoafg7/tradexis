package com.example.trading_webapp_backend.controller;

import com.example.trading_webapp_backend.model.Trade;
import com.example.trading_webapp_backend.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TradeController {

    @Autowired
    private TradeRepository tradeRepository;

    @GetMapping("/trades")
    public ResponseEntity<List<Trade>> getAll() {
        List<Trade> trade = tradeRepository.getAll();
        if (trade == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trade);
    }

    @GetMapping("/trades")
    public ResponseEntity<Optional<Trade>> getById(@RequestParam int id) {
        Optional<Trade> trade = tradeRepository.findById(id);
        if (trade == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trade);
    }
}