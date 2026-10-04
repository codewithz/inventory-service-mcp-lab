package com.training.inventory.controller;

import com.training.inventory.dto.BulkStockItem;
import com.training.inventory.service.BulkStockService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock-levels/bulk")
public class BulkStockController {

    @Autowired
    private BulkStockService bulkStockService;

    @PutMapping
    public String bulkUpdate(@RequestBody List<BulkStockItem> items) {
        return bulkStockService.update(items);
    }
}
