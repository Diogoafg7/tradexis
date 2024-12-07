package com.example.trading_webapp_backend.controller;

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

    @GetMapping("/balance/{id}")
    public ResponseEntity<Wallet> getBalanceById(@PathVariable Long id) {
        return ResponseEntity.ok(walletService.getBalanceById(Math.toIntExact(id)));
    }

    @GetMapping("/balance/{user_id}")
    public ResponseEntity<Wallet> getBalanceByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(walletService.getBalanceByUserId(Math.toIntExact(userId)));
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@Valid @RequestBody Wallet wallet) {
        try {
            return ResponseEntity.ok(walletService.createWallet(wallet));
        } catch (CustomExceptions.ItemsCreationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (CustomExceptions.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wallet> updateWalletById(@PathVariable Long id, @Valid @RequestBody Wallet wallet) {
        try {
            return ResponseEntity.ok(walletService.updateWalletById(Math.toIntExact(id), wallet));
        } catch (CustomExceptions.ItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<Wallet> getWalletByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(walletService.getWalletByUserId(Math.toIntExact(userId)));
    }

    @PostMapping("/user/{userId}/balance/{balance}")
    public ResponseEntity<Wallet> createWallet(@PathVariable Long userId, @PathVariable Double balance) {
        try {
            return ResponseEntity.ok(walletService.createWallet(Math.toIntExact(userId), balance));
        } catch (CustomExceptions.ItemsCreationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (CustomExceptions.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/user/{userId}/balance/{balance}")
    public ResponseEntity<Wallet> updateWalletBalance(@PathVariable Long userId, @PathVariable Double balance) {
        try {
            return ResponseEntity.ok(walletService.updateWalletBalance(Math.toIntExact(userId), balance));
        } catch (CustomExceptions.ItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (CustomExceptions.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteWalletByUserId(@PathVariable Long userId) {
        try {
            walletService.deleteWalletByUserId(Math.toIntExact(userId));
            return ResponseEntity.ok().build();
        } catch (CustomExceptions.ItemsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAllWallets() {
        walletService.deleteAllWallets();
        return ResponseEntity.ok().build();
    }



    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(CustomExceptions.ItemsNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(CustomExceptions.ItemsCreationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
