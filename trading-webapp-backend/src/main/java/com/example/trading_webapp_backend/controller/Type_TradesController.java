package com.example.trading_webapp_backend.controller;

import com.example.trading_webapp_backend.service.Type_TradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type_trades")
public class Type_TradesController {

    @Autowired
    private Type_TradesService typeTradesService;

    @GetMapping("/list")
    public String listTypeTrades() {
        return typeTradesService.getAllTypeTrades().toString();
    }

    @GetMapping("/get/{id}")
    public String getTypeAssetById(@PathVariable int id) {
        return typeTradesService.getTypeTradesById(id).toString();
    }

    @GetMapping("/getByName/{name}")
    public String getTypeAssetByName(@PathVariable String name) {
        return typeTradesService.getTypeTradesByName(name).toString();
    }

    @GetMapping("/populate")
    public String populateTypeTrades() {
        typeTradesService.populateTrades();
        return "Type_Trades populated with default values!";
    }

    @GetMapping("/add/{name}")
    public String addTypeTrades(@PathVariable String name) {
        typeTradesService.addTypeTrades(name);
        return "Type_Trades added successfully!";
    }

    @GetMapping("/update/{id}/{name}")
    public String updateTypeTrades(@PathVariable int id, @PathVariable String name) {
        typeTradesService.updateTypeTrades(id, name);
        return "Type_Trades updated successfully!";
    }

    @GetMapping("/delete/{id}")
    public String deleteTypeTrades(@PathVariable int id) {
        typeTradesService.deleteTypeTrades(id);
        return "Type_Trades deleted successfully!";
    }
}
