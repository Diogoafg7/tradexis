package com.example.trading_webapp_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Type_Assets ")
public class Type_Assets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is mandatory")
    @Column(name= "name",nullable = false)
    private String name; //VALUES ('Stock'), ('Crypto'), ('Forex')

    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Type_Assets(String name) {
        this.name = name;
    }

    //prePersist
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
