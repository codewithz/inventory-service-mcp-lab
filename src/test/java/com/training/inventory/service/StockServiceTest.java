package com.training.inventory.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.training.inventory.dto.StockResponse;
import com.training.inventory.entity.ProductStock;
import com.training.inventory.exception.InsufficientStockException;
import com.training.inventory.exception.StockNotFoundException;
import com.training.inventory.repository.ProductStockRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private ProductStockRepository repository;

    @Mock
    private AlertNotificationService alerts;

    private StockService service;

    @BeforeEach
    void setUp() {
        service = new StockService(repository, alerts);
    }

    @Test
    void getStockReturnsCurrentQuantity() {
        when(repository.findByProductId("SKU-1001")).thenReturn(Optional.of(new ProductStock("SKU-1001", 120)));

        StockResponse response = service.getStock("SKU-1001");

        assertThat(response.quantity()).isEqualTo(120);
    }

    @Test
    void getStockThrowsWhenProductUnknown() {
        when(repository.findByProductId("SKU-9999")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getStock("SKU-9999")).isInstanceOf(StockNotFoundException.class);
    }

    @Test
    void adjustStockAddsPositiveDelta() {
        when(repository.findByProductId("SKU-1001")).thenReturn(Optional.of(new ProductStock("SKU-1001", 20)));
        when(repository.save(any(ProductStock.class))).thenAnswer(inv -> inv.getArgument(0));

        StockResponse response = service.adjustStock("SKU-1001", 5);

        assertThat(response.quantity()).isEqualTo(25);
        verify(alerts, never()).notifyLowStock(any(), org.mockito.ArgumentMatchers.anyInt());
    }

    @Test
    void adjustStockSendsAlertWhenStockFallsToThreshold() {
        when(repository.findByProductId("SKU-1001")).thenReturn(Optional.of(new ProductStock("SKU-1001", 12)));
        when(repository.save(any(ProductStock.class))).thenAnswer(inv -> inv.getArgument(0));

        service.adjustStock("SKU-1001", -4);

        verify(alerts).notifyLowStock("SKU-1001", 8);
    }

    @Test
    void adjustStockRejectsResultBelowZero() {
        when(repository.findByProductId("SKU-1002")).thenReturn(Optional.of(new ProductStock("SKU-1002", 3)));

        assertThatThrownBy(() -> service.adjustStock("SKU-1002", -5)).isInstanceOf(InsufficientStockException.class);
    }
}
