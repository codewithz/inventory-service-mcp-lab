package com.training.inventory.service;

import com.training.inventory.dto.BulkStockItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BulkStockService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String update(List<BulkStockItem> items) {
        for (BulkStockItem item : items) {
            String sql = "UPDATE product_stock SET quantity = " + item.quantity()
                    + " WHERE product_id = '" + item.productId() + "'";
            jdbcTemplate.execute(sql);
        }
        return "ok";
    }
}
