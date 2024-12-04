package com.example.trading_webapp_backend.service.impl;

import com.example.trading_webapp_backend.model.Assets;
import com.example.trading_webapp_backend.model.Type_Assets;
import com.example.trading_webapp_backend.repository.AssetsRepository;
import com.example.trading_webapp_backend.repository.Type_AssetsRepository;
import com.example.trading_webapp_backend.service.AssetsService;
import com.example.trading_webapp_backend.service.Type_AssetsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssetsServiceImpl implements AssetsService {

    private static final Logger logger = LoggerFactory.getLogger(AssetsServiceImpl.class);

    @Autowired
    private AssetsRepository assetsRepository;

    @Autowired
    private Type_AssetsRepository typeAssetsRepository;

    @Autowired
    private Type_AssetsService typeAssetsService;

    private final String FINNHUB_API_KEY = "ct6ttmhr01qr3sdsuv60ct6ttmhr01qr3sdsuv6g";
    private final String FINNHUB_URL = "https://finnhub.io/api/v1/stock/symbol?exchange=US&token=" + FINNHUB_API_KEY;

    private final WebClient webClient = WebClient.create();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Assets> getAllAssets() {
        logger.info("Iniciando a obtenção de todos os ativos.");
        try {
            List<Assets> assets = assetsRepository.findAll();
            logger.info("Foram encontrados {} ativos na base de dados.", assets.size());
            return assets;
        } catch (Exception e) {
            logger.error("Erro ao buscar todos os ativos: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao buscar ativos.");
        }
    }

    @Override
    public Assets getAssetById(int id) {
        return assetsRepository.findById(id).orElse(null);
    }

    @Override
    public Assets getAssetBySymbol(String symbol) {
        return assetsRepository.findBySymbol(symbol).orElse(null);
    }

    @Override
    public void updateAssetPrice(int id, double price) {
        logger.info("Iniciando atualização do preço do ativo com ID: {}", id);

        try {
            Assets asset = getAssetById(id);
            if (asset != null) {
                if (price > 0) {
                    logger.info("Atualizando o preço do ativo {} de {} para {}", asset.getSymbol(), asset.getPrice(), price);
                    asset.setPrice(price);
                    assetsRepository.save(asset);
                    logger.info("Preço atualizado com sucesso para o ativo {}.", asset.getSymbol());
                } else {
                    logger.warn("Preço inválido para o ativo {}: {}. Atualização ignorada.", asset.getSymbol(), price);
                }
            } else {
                logger.warn("Nenhum ativo encontrado com o ID: {}. Atualização interrompida.", id);
            }
        } catch (Exception e) {
            logger.error("Erro ao atualizar preço do ativo com ID {}: {}", id, e.getMessage(), e);
        }
    }

    @Override
    public void updateAssetsPricesFromApi() {
        logger.info("Iniciando atualização de preços dos ativos.");
        List<Assets> assetsList = assetsRepository.findAll();
        if (assetsList.isEmpty()) {
            logger.warn("Nenhum ativo encontrado na base de dados. Atualização interrompida.");
            return;
        }

        logger.info("Encontrados {} ativos para atualização.", assetsList.size());
        List<Assets> updatedAssets = new ArrayList<>();

        for (Assets asset : assetsList) {
            try {
                logger.info("Obtendo preço para o ativo: {}", asset.getSymbol());
                String jsonResponse = getAssetPrice(asset.getSymbol());

                JsonNode responseNode = objectMapper.readTree(jsonResponse);
                if (responseNode.has("currentPrice")) {
                    double price = responseNode.get("currentPrice").asDouble();
                    asset.setPrice(price);
                    updatedAssets.add(asset);
                } else {
                    logger.warn("Nenhum preço encontrado para o ativo: {}", asset.getSymbol());
                }
            } catch (Exception e) {
                logger.error("Erro ao atualizar o preço do ativo {}: {}", asset.getSymbol(), e.getMessage(), e);
            }
        }

        if (!updatedAssets.isEmpty()) {
            try {
                assetsRepository.saveAll(updatedAssets);
                logger.info("Preços atualizados com sucesso! {} ativos atualizados.", updatedAssets.size());
            } catch (Exception e) {
                logger.error("Erro ao salvar os preços atualizados: {}", e.getMessage(), e);
            }
        } else {
            logger.info("Nenhum ativo teve o preço atualizado.");
        }
    }

    @Override
    public String getAssetPrice(String symbol) {
        String url = String.format("https://finnhub.io/api/v1/quote/?symbol=%s&token=%s", symbol, FINNHUB_API_KEY);
        logger.info("Enviando requisição para API Finnhub: {}", url);

        try {
            String response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            logger.info("Resposta JSON recebida para {}: {}", symbol, response);

            JsonNode jsonResponse = objectMapper.readTree(response);
            double currentPrice = jsonResponse.path("c").asDouble(0.0);

            if (currentPrice > 0) {
                ObjectNode outputJson = objectMapper.createObjectNode();
                outputJson.put("symbol", symbol);
                outputJson.put("currentPrice", currentPrice);
                return outputJson.toString();
            } else {
                logger.warn("Preço inválido ou não encontrado para o ativo: {}", symbol);
            }
        } catch (Exception e) {
            logger.error("Erro ao obter preço do ativo {}: {}", symbol, e.getMessage(), e);
        }

        ObjectNode errorJson = objectMapper.createObjectNode();
        errorJson.put("symbol", symbol);
        errorJson.put("error", "Não foi possível obter o preço do ativo.");
        return errorJson.toString();
    }

    @Override
    public void importAssetsFromFile() {
        // Garante que os tipos de ativos estão na base de dados
        typeAssetsService.populateTypes();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("data/top_3_stocks.csv").getInputStream()))) {

            String line = reader.readLine(); // Ignorar cabeçalho
            if (line == null) {
                throw new IOException("O ficheiro esta vazio ou não foi encontrado.");
            }

            List<Assets> assetsList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length < 2) {
                    logger.warn("Linha mal formatada ignorada: {}", line);
                    continue;
                }

                String symbol = values[0].trim();
                String name = values[1].trim();

                if (symbol.isEmpty() || name.isEmpty()) {
                    logger.warn("Linha com dados invalidos ignorada: {}", line);
                    continue;
                }

                Assets asset = assetsRepository.findBySymbol(symbol).orElse(null);
                if (asset == null) {
                    asset = new Assets();
                    asset.setSymbol(symbol);
                    asset.setPrice(0.0); // Preço inicial como 0
                    Type_Assets stockType = typeAssetsService.getTypeAssetByName("Stock");
                    asset.setTypeId(stockType);
                }

                asset.setName(name);
                asset.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));

                assetsRepository.saveOrUpdateAsset(
                        asset.getSymbol(),
                        asset.getName(),
                        asset.getPrice(),
                        (long) asset.getTypeId().getId(),
                        asset.getCreatedAt()
                );
            }

            logger.info("Importacao concluida com sucesso! {} ativos importados.", assetsList.size());

        } catch (IOException e) {
            logger.error("Erro ao aceder ou ler o ficheiro: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Erro nos dados fornecidos: {}", e.getMessage());
        }
    }

    @Override
    public void saveOrUpdateAsset(String symbol, String name, Double price, Long typeId, LocalDateTime createdAt) {
        Optional<Assets> existingAsset = assetsRepository.findBySymbol(symbol);

        if (existingAsset.isPresent()) {
            Assets asset = existingAsset.get();
            asset.setName(name);
            asset.setPrice(price);
            asset.setTypeId(typeAssetsRepository.findById(Math.toIntExact(typeId)).orElseThrow());
            asset.setCreatedAt(createdAt);
            assetsRepository.save(asset);
        } else {
            Assets newAsset = new Assets(name, symbol, typeAssetsRepository.findById(Math.toIntExact(typeId)).orElseThrow(), price, createdAt);
            assetsRepository.save(newAsset);
        }
    }
}
