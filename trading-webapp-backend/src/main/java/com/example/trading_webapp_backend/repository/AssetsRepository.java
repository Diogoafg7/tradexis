package com.example.trading_webapp_backend.repository;

import com.example.trading_webapp_backend.model.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AssetsRepository extends JpaRepository<Assets, Integer> {

    Optional<Assets> findBySymbol(String symbol);

    // Método para salvar ou atualizar o ativo
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO assets (symbol, name, price, type_id, created_at) " +
            "VALUES (:symbol, :name, :price, :typeId, :createdAt) " +
            "ON DUPLICATE KEY UPDATE " +
            "name = VALUES(name), price = VALUES(price), type_id = VALUES(type_id)",
            nativeQuery = true)
    void saveOrUpdateAsset(@Param("symbol") String symbol,
                           @Param("name") String name,
                           @Param("price") Double price,
                           @Param("typeId") Long typeId,
                           @Param("createdAt") LocalDateTime createdAt);
}
