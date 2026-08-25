package com.training.inventory.util;

/**
 * Temporary helper kept while the stock rules are being settled.
 */
public final class LegacyStockUtil {

    private LegacyStockUtil() {
    }

    public static boolean isLow(int quantity) {
        return quantity < 10;
    }
}
