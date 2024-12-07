package com.example.trading_webapp_backend.controller;

import com.example.trading_webapp_backend.dtos.WalletDTO;
import com.example.trading_webapp_backend.exception.CustomExceptions;
import com.example.trading_webapp_backend.model.Wallet;
import com.example.trading_webapp_backend.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {
    @Autowired
    private final WalletService walletService;

    @GetMapping()
    public ResponseEntity<List<Wallet>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long id) {
        return ResponseEntity.ok(walletService.getWalletById(Math.toIntExact(id)));
    }

    @GetMapping("/wallet/{username}")
    public ResponseEntity<Wallet> getWalletByUsername(@PathVariable String username) {
        return ResponseEntity.ok(walletService.getWalletByUsername(username));
    }

    @PutMapping("/{walletId}/addFunds")
    public ResponseEntity<Wallet> addFunds(
            @PathVariable int walletId,
            @RequestParam double amount) {
        Wallet updatedWallet = walletService.addFundsToWallet(walletId, amount);
        return ResponseEntity.ok(updatedWallet);
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@Valid @RequestBody WalletDTO wallet) {
        try {
            return ResponseEntity.ok(walletService.createWallet(wallet));
        } catch (CustomExceptions.ItemsCreationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (CustomExceptions.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        try {
            walletService.deleteWallet(Math.toIntExact(id));
            return ResponseEntity.ok().build();
        } catch (CustomExceptions.ItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
