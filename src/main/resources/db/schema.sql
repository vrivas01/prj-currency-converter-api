CREATE TABLE IF NOT EXISTS currency_conversion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(19, 4),
    currency_origin VARCHAR(10),
    currency_destination VARCHAR(10),
    amount_exchange_rate DECIMAL(19, 6),
    exchange_rate DECIMAL(19, 6),
    registered_at TIMESTAMP
);