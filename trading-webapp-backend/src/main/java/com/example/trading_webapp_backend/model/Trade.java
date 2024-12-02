package com.example.trading_webapp_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Trades")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id") // Cria a chave estrangeira user_id
    private User user; // Relacionar com a entidade User

    //Descomentar depois de criar classe asset
    //@ManyToOne
    //@JoinColumn(name = "asset_id")
    //private Asset asset;
    private int asset_id;

    //Descomentar depois de criar classe type_trades
    //@ManyToOne
    //@JoinColumn(name = "type_trades_id")
    //private Trade_type trade_type_id;
    private int trade_type_id;

    private double quantity;
    private double price;
    private double total;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters e Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(int asset_id) {
        this.asset_id = asset_id;
    }

    public int getTrade_type_id() {
        return trade_type_id;
    }

    public void setTrade_type_id(int trade_type_id) {
        this.trade_type_id = trade_type_id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = roundToTwoDecimalPlaces(quantity);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = roundToTwoDecimalPlaces(price);
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = roundToTwoDecimalPlaces(total);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Trade(int id, User user_id, int asset_id, int trade_type_id, double quantity, double price, double total,LocalDateTime createdAt) {
        this.id = id;
        this.user = user_id;
        this.asset_id = asset_id;
        this.trade_type_id = trade_type_id;
        this.quantity = roundToTwoDecimalPlaces(quantity);
        this.price = roundToTwoDecimalPlaces(price);
        this.total = roundToTwoDecimalPlaces(total);
        this.createdAt = createdAt;
    }

    // toString
    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", user_id='" + user + '\'' +
                ", asset_id='" + asset_id + '\'' +
                ", trade_type_id='" + trade_type_id + '\'' +
                ", quantity=" + String.format("%.2f", quantity) +
                ", price=" + String.format("%.2f", price) +
                ", total=" + String.format("%.2f", total) +
                ", createdAt=" + createdAt +
                '}';
    }

    // Método para arredondar valores para 2 casas decimais
    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}


