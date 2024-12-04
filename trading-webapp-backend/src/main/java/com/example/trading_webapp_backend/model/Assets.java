package com.example.trading_webapp_backend.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@Entity
@Table(name = "Assets")
public class Assets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is mandatory")
    @Column(name= "name",nullable = false)
    private String name;

    @NotBlank(message = "Symbol is mandatory")
    @Column(name= "symbol",nullable = false, unique = true)
    private String symbol;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", nullable = false)
    private Type_Assets  typeId;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0.0")
    @Column(name = "price",nullable = false)
    private double price;

    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Assets(String name, String symbol, Type_Assets typeId, double price) {
        this.name = name;
        this.symbol = symbol;
        this.typeId = typeId;
        this.price = price;
    }

    public Assets(String name, String symbol, Type_Assets typeId, double price, LocalDateTime createdAt) {
        this.name = name;
        this.symbol = symbol;
        this.typeId = typeId;
        this.price = price;
        this.createdAt = createdAt;
    }

    public Assets(int id, String name, String symbol, Type_Assets typeId, double price, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.typeId = typeId;
        this.price = price;
        this.createdAt = createdAt;
    }

    public Assets() {
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();  // Atribui o momento atual antes de persistir
        }
    }

}
