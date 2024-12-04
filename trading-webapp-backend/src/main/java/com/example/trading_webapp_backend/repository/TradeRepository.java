package com.example.trading_webapp_backend.repository;

import com.example.trading_webapp_backend.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer>{
    List<Trade> getAll();

    //List<Trade> findAll(Sort sort); --Ordenar desc, asc - data de criação

    //    @Query("SELECT t FROM Trade t WHERE t.id = :id")
    Trade getById(int id);
    //Total de trades de um determinado id
    @Query("SELECT COUNT(t) FROM Trades t WHERE t.user.id = :user_id")
    long countByUserId(@Param("user_id") int user_id);

    Trade getByUserId(int user_id);

    Trade getByAssetId(int asset_id);
    // 11. Obter o total de trades feitas num asset específico
    @Query("SELECT COUNT(t) FROM Trades t WHERE t.asset_id = :asset_id")
    long countByAssetId(@Param("asset_id") int asset_id);

    Trade getByTypeId(int type_id);
    // Obter a soma do total de trades por tipo de trade
    @Query("SELECT SUM(t.total) FROM Trades t WHERE t.trade_type_id = :trade_type_id")
    Double sumTotalByTradeTypeId(@Param("trade_type_id") int trade_type_id);

    List<Trade> getByQuantityGreaterThan(double quantity);

    List<Trade> getByTotalGreaterThan(double total);

    //Procurar entre 2 datas, procurar por preço dentro de intervalo,
    //Procurar trades com valor maior que valor
}