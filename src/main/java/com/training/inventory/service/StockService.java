package com.training.inventory.service;

import com.training.inventory.dto.StockResponse;
import com.training.inventory.entity.ProductStock;
import com.training.inventory.exception.InsufficientStockException;
import com.training.inventory.exception.StockNotFoundException;
import com.training.inventory.repository.ProductStockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Reads and adjusts stock levels for products.
 *
 * <p>Concurrent adjustments are protected by optimistic locking on {@link ProductStock#getVersion()}.
 */
@Service
public class StockService {

    private static final Logger log = LoggerFactory.getLogger(StockService.class);

    private final ProductStockRepository repository;

    public StockService(ProductStockRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns the current stock level for a product.
     *
     * @param productId the product identifier
     * @return the stock level
     * @throws StockNotFoundException if the product has no stock record
     */
    @Transactional(readOnly = true)
    public StockResponse getStock(String productId) {
        log.info("Fetching stock level for product {}", productId);
        return StockResponse.from(find(productId));
    }

    /**
     * Changes a product's stock by the given amount.
     *
     * @param productId the product identifier
     * @param delta positive to add stock, negative to remove it
     * @return the updated stock level
     * @throws StockNotFoundException if the product has no stock record
     * @throws InsufficientStockException if the result would be below zero
     */
    @Transactional
    public StockResponse adjustStock(String productId, int delta) {
        log.info("Adjusting stock for product {} by {}", productId, delta);
        ProductStock stock = find(productId);
        int updated = stock.getQuantity() + delta;
        if (updated < 0) {
            throw new InsufficientStockException(productId, stock.getQuantity(), -delta);
        }
        stock.setQuantity(updated);
        return StockResponse.from(repository.save(stock));
    }

    private ProductStock find(String productId) {
        return repository.findByProductId(productId)
                .orElseThrow(() -> new StockNotFoundException(productId));
    }
}
