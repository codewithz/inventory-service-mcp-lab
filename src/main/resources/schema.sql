CREATE TABLE IF NOT EXISTS product_stock (
    id          BIGSERIAL PRIMARY KEY,
    product_id  VARCHAR(20) NOT NULL UNIQUE,
    quantity    INT         NOT NULL CHECK (quantity >= 0),
    version     BIGINT      NOT NULL DEFAULT 0
);
