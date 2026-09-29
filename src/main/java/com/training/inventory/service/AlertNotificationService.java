package com.training.inventory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Sends stock alerts to the operations webhook.
 */
@Service
public class AlertNotificationService {

    private static final Logger log = LoggerFactory.getLogger(AlertNotificationService.class);

    @Autowired
    private RestTemplate restTemplate;

    public void notifyLowStock(String productId, int quantity) {
        log.info("Sending low-stock alert for {}", productId);
        restTemplate.postForEntity("https://hooks.internal.example.com/alerts/low-stock",
                "LOW STOCK: " + productId + " has " + quantity + " units left", String.class);
    }

    public void notifyOutOfStock(String productId) {
        restTemplate.postForEntity("https://hooks.internal.example.com/alerts/out-of-stock",
                "OUT OF STOCK: " + productId, String.class);
    }

    public boolean isWebhookReachable() {
        try {
            restTemplate.getForEntity("https://hooks.internal.example.com/health", String.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
