package com.example.trading_webapp_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Builder
@Entity
@Table(name = "Trades")
public class Trades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "asset_id", nullable = false)
    private Assets asset;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trade_type_id", nullable = false)
    private Type_Trades tradeType;

    @NotNull(message = "Quantity cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Quantity must be greater than or equal to 0.0")
    @Column(name = "quantity", nullable = false)
    private double quantity;

    @NotNull(message = "Total cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total must be greater than or equal to 0.0")
    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Trades(User user, Assets asset, Type_Trades tradeType, double quantity, double total) {
        this.user = user;
        this.asset = asset;
        this.tradeType = tradeType;
        this.quantity = quantity;
        this.total = total;
    }

    public Trades(User user, Assets asset, Type_Trades tradeType, double quantity, double total, LocalDateTime createdAt) {
        this.user = user;
        this.asset = asset;
        this.tradeType = tradeType;
        this.quantity = quantity;
        this.total = total;
        this.createdAt = createdAt;
    }

    public Trades(int id, User user, Assets asset, Type_Trades tradeType, double quantity, double total, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.asset = asset;
        this.tradeType = tradeType;
        this.quantity = quantity;
        this.total = total;
        this.createdAt = createdAt;
    }

    public Trades() {
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}

