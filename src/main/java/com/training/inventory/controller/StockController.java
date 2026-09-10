package com.training.inventory.controller;

import com.training.inventory.dto.StockAdjustmentRequest;
import com.training.inventory.dto.StockResponse;
import com.training.inventory.service.LowStockService;
import com.training.inventory.service.StockService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoints for reading and adjusting stock levels.
 */
@RestController
@RequestMapping("/api/v1/stock-levels")
public class StockController {

    private final StockService stockService;
    private final LowStockService lowStockService;

    public StockController(StockService stockService, LowStockService lowStockService) {
        this.stockService = stockService;
        this.lowStockService = lowStockService;
    }

    /**
     * Returns the current stock level for a product.
     *
     * @param productId the product identifier
     * @return the stock level
     */
    @GetMapping("/{productId}")
    public StockResponse getStock(@PathVariable String productId) {
        return stockService.getStock(productId);
    }

    /**
     * Adjusts a product's stock by a positive or negative amount.
     *
     * @param productId the product identifier
     * @param request the adjustment to apply
     * @return the updated stock level
     */
    @PostMapping("/{productId}/adjustments")
    public StockResponse adjustStock(@PathVariable String productId,
                                     @Valid @RequestBody StockAdjustmentRequest request) {
        return stockService.adjustStock(productId, request.delta());
    }

    /**
     * Lists products at or below their low-stock threshold.
     *
     * @return the low-stock products
     */
    @GetMapping("/low")
    public List<StockResponse> lowStock() {
        return lowStockService.findLowStock();
    }
}
