package com.training.inventory.dto;

/**
 * Request to change a product's stock by a positive or negative amount.
 */
public record StockAdjustmentRequest(Integer delta) {
}
