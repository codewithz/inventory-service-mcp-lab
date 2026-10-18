package com.training.inventory.dto;

import jakarta.validation.constraints.NotNull;

/**
 * Request to change a product's stock by a positive or negative amount.
 */
public record StockAdjustmentRequest(@NotNull Integer delta) {
}
