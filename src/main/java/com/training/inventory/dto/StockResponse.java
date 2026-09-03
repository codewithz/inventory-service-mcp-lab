package com.training.inventory.dto;

import com.training.inventory.entity.ProductStock;

/**
 * Stock level returned by the API.
 */
public record StockResponse(String productId, int quantity, int lowStockThreshold) {

    public static StockResponse from(ProductStock stock) {
        return new StockResponse(stock.getProductId(), stock.getQuantity(), stock.getLowStockThreshold());
    }
}
