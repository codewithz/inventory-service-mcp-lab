package com.training.inventory.repository;

import com.training.inventory.entity.ProductStock;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Persistence access for {@link ProductStock}.
 */
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {

    Optional<ProductStock> findByProductId(String productId);

    @Query("select p from ProductStock p where p.quantity <= p.lowStockThreshold order by p.quantity")
    List<ProductStock> findLowStock();
}
