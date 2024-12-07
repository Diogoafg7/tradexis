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
@Table(name = "Type_Trades ")
public class Type_Trades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is mandatory")
    @Column(name= "name",nullable = false)
    private String name; //VALUES ('Buy'), ('Sell')

    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
