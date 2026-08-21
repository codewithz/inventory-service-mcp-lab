package com.training.inventory.exception;

/**
 * Thrown when no stock record exists for a product.
 */
public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(String productId) {
        super("No stock record for product " + productId);
    }
}
