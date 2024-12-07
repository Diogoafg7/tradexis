package com.example.trading_webapp_backend.service;

import com.example.trading_webapp_backend.model.Type_Assets;

import java.util.List;

public interface Type_AssetsService {
    List<Type_Assets> getAllTypeAssets();
    Type_Assets getTypeAssetById(int id);
    Type_Assets getTypeAssetByName(String name);
    void addTypeAsset(Type_Assets typeAsset);
    void updateTypeAsset(int id, Type_Assets typeAsset);
    void deleteTypeAsset(int id);
    void addTypeAsset(String name);
    //add types VALUES ('Stock'), ('Crypto'), ('Forex')
    void populateTypes();
}
