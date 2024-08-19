CREATE Table transactions(
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    amount DECIMAL NOT NULL,
    payer_id BIGINT,
    payee_id BIGINT,
    FOREIGN KEY (payer_id) REFERENCES users(id),
    FOREIGN KEY (payee_id) REFERENCES users(id)
);