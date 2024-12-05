package com.example.trading_webapp_backend.service;

import com.example.trading_webapp_backend.model.Portfolio;

import java.util.List;

public interface PortfolioService {
    List<Portfolio> getPortfolio(String username);
    List<Portfolio> getAllPortfolios();
    void createPortfolio(String username);
    void deletePortfolio(String username);
    void addStock(String username, String stockSymbol, double  quantity);
    void removeStock(String username, String stockSymbol);
    void updateStock(String username, String stockSymbol, double  quantity);
    void createPortfolioForAllUsers();
}
