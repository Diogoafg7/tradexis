package com.example.trading_webapp_backend.controller;

import com.example.trading_webapp_backend.model.Portfolio;
import com.example.trading_webapp_backend.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    //getPortfolio
    @GetMapping("/{username}")
    public List<Portfolio> getPortfolio(@PathVariable String username) {
        return portfolioService.getPortfolio(username);
    }

    //getAllPortfolios
    @GetMapping("/list")
    public List<Portfolio> getAllPortfolios() {
        return portfolioService.getAllPortfolios();
    }

    //createPortfolio
    @GetMapping("/create/{username}")
    public void createPortfolio(@PathVariable String username) {
        portfolioService.createPortfolio(username);
    }

    //deletePortfolio
    @GetMapping("/delete/{username}")
    public void deletePortfolio(@PathVariable String username) {
        portfolioService.deletePortfolio(username);
    }

    //addStock
    @GetMapping("/add/{username}/{stockSymbol}/{quantity}")
    public void addStock(@PathVariable String username, @PathVariable String stockSymbol, @PathVariable double quantity) {
        portfolioService.addStock(username, stockSymbol, quantity);
    }

    //removeStock
    @GetMapping("/remove/{username}/{stockSymbol}")
    public void removeStock(@PathVariable String username, @PathVariable String stockSymbol) {
        portfolioService.removeStock(username, stockSymbol);
    }

    //updateStock
    @GetMapping("/update/{username}/{stockSymbol}/{quantity}")
    public void updateStock(@PathVariable String username, @PathVariable String stockSymbol, @PathVariable double quantity) {
        portfolioService.updateStock(username, stockSymbol, quantity);
    }

    //createPortfolioForAllUsers
    @GetMapping("/create-all")
    public void createPortfolioForAllUsers() {
        portfolioService.createPortfolioForAllUsers();
    }
}
