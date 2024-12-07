package com.example.trading_webapp_backend.controller;

import com.example.trading_webapp_backend.service.Type_AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type_assets")
public class Type_AssetsController {

    @Autowired
    private Type_AssetsService typeAssetsService;

    @GetMapping("/list")
    public String listTypeAssets() {
        return typeAssetsService.getAllTypeAssets().toString();
    }

    @GetMapping("/get/{id}")
    public String getTypeAssetById(@PathVariable int id) {
        return typeAssetsService.getTypeAssetById(id).toString();
    }

    @GetMapping("/getByName/{name}")
    public String getTypeAssetByName(@PathVariable String name) {
        return typeAssetsService.getTypeAssetByName(name).toString();
    }

    @GetMapping("/populate")
    public String populateTypeAssets() {
        typeAssetsService.populateTypes();
        return "Type_Assets populated with default values!";
    }

    @GetMapping("/add/{name}")
    public String addTypeAsset(@PathVariable String name) {
        typeAssetsService.addTypeAsset(name);
        return "Type_Asset added successfully!";
    }
}
