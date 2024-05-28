CREATE TABLE IF NOT EXISTS users (
 id BIGSERIAL PRIMARY KEY,
 username VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS products (
 id BIGSERIAL PRIMARY KEY,
 user_id BIGINT NOT NULL,
 account_number BIGINT NOT NULL,
 balance NUMERIC(10,2) CONSTRAINT balance_positive CHECK (balance >= 0),
 product_type VARCHAR(20),
 FOREIGN KEY (user_id) REFERENCES users (id)
);