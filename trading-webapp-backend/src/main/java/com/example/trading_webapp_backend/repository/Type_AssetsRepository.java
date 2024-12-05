package com.example.trading_webapp_backend.repository;

import com.example.trading_webapp_backend.model.Type_Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Type_AssetsRepository extends JpaRepository<Type_Assets, Integer> {
    Type_Assets findByName(String name);

}
