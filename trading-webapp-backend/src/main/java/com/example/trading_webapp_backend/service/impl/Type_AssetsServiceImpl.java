package com.example.trading_webapp_backend.service.impl;

import com.example.trading_webapp_backend.model.Type_Assets;
import com.example.trading_webapp_backend.repository.Type_AssetsRepository;
import com.example.trading_webapp_backend.service.Type_AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Type_AssetsServiceImpl implements Type_AssetsService {

    @Autowired
    private Type_AssetsRepository typeAssetsRepository;

    @Override
    public List<Type_Assets> getAllTypeAssets() {
        return typeAssetsRepository.findAll();
    }

    @Override
    public Type_Assets getTypeAssetById(int id) {
        return typeAssetsRepository.findById(id).orElse(null);
    }

    @Override
    public Type_Assets getTypeAssetByName(String name) {
        return typeAssetsRepository.findByName(name);
    }

    @Override
    public void addTypeAsset(Type_Assets typeAsset) {
        if (typeAssetsRepository.findByName(typeAsset.getName()) == null) {
            typeAssetsRepository.save(typeAsset);
        } else {
            System.err.println("Tipo de ativo já existe: " + typeAsset.getName());
        }
    }

    @Override
    public void addTypeAsset(String name) {
        Type_Assets typeAsset = new Type_Assets();
        typeAsset.setName(name);
        addTypeAsset(typeAsset);
    }

    @Override
    public void updateTypeAsset(int id, Type_Assets typeAsset) {
        Type_Assets existingTypeAsset = getTypeAssetById(id);
        if (existingTypeAsset != null) {
            existingTypeAsset.setName(typeAsset.getName());
            typeAssetsRepository.save(existingTypeAsset);
        } else {
            System.err.println("Tipo de ativo com ID " + id + " não encontrado.");
        }
    }

    @Override
    public void deleteTypeAsset(int id) {
        if (typeAssetsRepository.existsById(id)) {
            typeAssetsRepository.deleteById(id);
        } else {
            System.err.println("Tipo de ativo com ID " + id + " não encontrado.");
        }
    }

    @Override
    public void populateTypes() {
        String[] predefinedTypes = {"Stock", "Crypto", "Forex"};
        for (String typeName : predefinedTypes) {
            if (typeAssetsRepository.findByName(typeName) == null) {
                Type_Assets typeAsset = new Type_Assets();
                typeAsset.setName(typeName);
                addTypeAsset(typeAsset);
            } else {
                System.out.println("Tipo de ativo já existe: " + typeName);
            }
        }
    }
}
