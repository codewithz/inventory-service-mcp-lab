package com.training.inventory.repository;

import com.training.inventory.entity.ProductStock;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Persistence access for {@link ProductStock}.
 */
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {

    Optional<ProductStock> findByProductId(String productId);
}
