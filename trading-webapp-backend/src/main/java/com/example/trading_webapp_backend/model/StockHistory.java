package com.example.trading_webapp_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
@Entity
@Table(name = "StockHistory")
public class StockHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "asset_id", nullable = false)
    private Assets asset;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    public StockHistory() {}

    public StockHistory(Assets asset, double price, LocalDateTime timestamp) {
        this.asset = asset;
        this.price = price;
        this.timestamp = timestamp;
    }

    public StockHistory(int id, Assets asset, double price, LocalDateTime timestamp) {
        this.id = id;
        this.asset = asset;
        this.price = price;
        this.timestamp = timestamp;
    }

    public void setAssetId(int assetId) {
        this.asset.setId(assetId);
    }

    public int getAssetId() {
        return this.asset.getId();
    }

    public void setAssetSymbol(String assetSymbol) {
        this.asset.setSymbol(assetSymbol);
    }

    public String getAssetSymbol() {
        return this.asset.getSymbol();
    }

    public void setAssetName(String assetName) {
        this.asset.setName(assetName);
    }

    public String getAssetName() {
        return this.asset.getName();
    }

    public void setAssetTypeId(int assetTypeId) {
        this.asset.getTypeId().setId(assetTypeId);
    }

    public int getAssetTypeId() {
        return this.asset.getTypeId().getId();
    }

    public void setAssetTypeName(String assetTypeName) {
        this.asset.getTypeId().setName(assetTypeName);
    }

    public String getAssetTypeName() {
        return this.asset.getTypeId().getName();
    }

    //pre persist
    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }
}
