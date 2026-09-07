package com.training.inventory.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.training.inventory.dto.StockResponse;
import com.training.inventory.entity.ProductStock;
import com.training.inventory.repository.ProductStockRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LowStockServiceTest {

    @Mock
    private ProductStockRepository repository;

    @InjectMocks
    private LowStockService service;

    @Test
    void findLowStockMapsRepositoryResults() {
        when(repository.findLowStock()).thenReturn(List.of(new ProductStock("SKU-1004", 3)));

        List<StockResponse> result = service.findLowStock();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).productId()).isEqualTo("SKU-1004");
        assertThat(result.get(0).lowStockThreshold()).isEqualTo(10);
    }

    @Test
    void findLowStockReturnsEmptyListWhenNothingIsLow() {
        when(repository.findLowStock()).thenReturn(List.of());

        assertThat(service.findLowStock()).isEmpty();
    }
}
