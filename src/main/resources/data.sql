INSERT INTO product_stock (product_id, quantity) VALUES
    ('SKU-1001', 120),
    ('SKU-1002', 8),
    ('SKU-1003', 45),
    ('SKU-1004', 3),
    ('SKU-1005', 260)
ON CONFLICT (product_id) DO NOTHING;
