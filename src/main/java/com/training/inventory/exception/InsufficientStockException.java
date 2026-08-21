package com.training.inventory.exception;

/**
 * Thrown when an adjustment would take stock below zero.
 */
public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(String productId, int available, int requested) {
        super("Insufficient stock for %s: available=%d, requested=%d".formatted(productId, available, requested));
    }
}
