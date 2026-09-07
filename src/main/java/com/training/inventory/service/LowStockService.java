package com.training.inventory.service;

import com.training.inventory.dto.StockResponse;
import com.training.inventory.repository.ProductStockRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Finds products whose stock has fallen to or below their configured threshold.
 */
@Service
public class LowStockService {

    private static final Logger log = LoggerFactory.getLogger(LowStockService.class);

    private final ProductStockRepository repository;

    public LowStockService(ProductStockRepository repository) {
        this.repository = repository;
    }

    /**
     * Lists products at or below their low-stock threshold, lowest quantity first.
     *
     * @return the low-stock products, or an empty list if none are low
     */
    @Transactional(readOnly = true)
    public List<StockResponse> findLowStock() {
        log.info("Querying products at or below their low-stock threshold");
        return repository.findLowStock().stream().map(StockResponse::from).toList();
    }
}
