package com.example.trading_webapp_backend.service.impl;

import com.example.trading_webapp_backend.model.Assets;
import com.example.trading_webapp_backend.model.Portfolio;
import com.example.trading_webapp_backend.model.User;
import com.example.trading_webapp_backend.repository.PortfolioRepository;
import com.example.trading_webapp_backend.repository.UserRepository;
import com.example.trading_webapp_backend.service.AssetsService;
import com.example.trading_webapp_backend.service.PortfolioService;
import com.example.trading_webapp_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository; // Usar repositório diretamente para evitar dependência circular

    @Autowired
    private AssetsService assetsService;

    @Override
    public List<Portfolio> getPortfolio(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return portfolioRepository.findByUser(user);
    }

    @Override
    public List<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }

    @Override
    public void createPortfolio(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Assets> assets = assetsService.getAllAssets();

        for (Assets asset : assets) {
            Portfolio portfolio = Portfolio.builder().user(user).asset(asset).quantity(0.0).build();
            portfolioRepository.save(portfolio);
        }

    }

    @Override
    public void deletePortfolio(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        portfolioRepository.findByUser(user).forEach(portfolioRepository::delete);
    }

    @Override
    public void addStock(String username, String stockSymbol, double quantity) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Assets asset = assetsService.getAssetBySymbol(stockSymbol);

        Portfolio portfolio = portfolioRepository.findByUserAndAsset(user, asset)
                .orElseGet(() -> Portfolio.builder().user(user).asset(asset).quantity(0.0).build());

        portfolio.setQuantity(portfolio.getQuantity() + quantity);
        portfolioRepository.save(portfolio);
    }

    @Override
    public void removeStock(String username, String stockSymbol) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Assets asset = assetsService.getAssetBySymbol(stockSymbol);

        Portfolio portfolio = portfolioRepository.findByUserAndAsset(user, asset)
                .orElseThrow(() -> new IllegalArgumentException("Portfolio entry not found"));

        portfolioRepository.delete(portfolio);
    }

    @Override
    public void updateStock(String username, String stockSymbol, double quantity) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Assets asset = assetsService.getAssetBySymbol(stockSymbol);

        Portfolio portfolio = portfolioRepository.findByUserAndAsset(user, asset)
                .orElseThrow(() -> new IllegalArgumentException("Portfolio entry not found"));

        portfolio.setQuantity(quantity);
        portfolioRepository.save(portfolio);
    }

    @Override
    public void createPortfolioForAllUsers() {
        List<User> users = userRepository.findAll();
        List<Assets> assets = assetsService.getAllAssets();

        for (User user : users) {
            for (Assets asset : assets) {
                Portfolio portfolio = Portfolio.builder().user(user).asset(asset).quantity(0.0).build();
                portfolioRepository.save(portfolio);
            }
        }
    }
}