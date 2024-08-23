CREATE TABLE transactions(
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    amount DECIMAL NOT NULL,
    payer_id INTEGER NOT NULL,
    payee_id INTEGER NOT NULL,
    transaction_date TIMESTAMP NOT NULL,
    FOREIGN KEY (payer_id) REFERENCES users(id),
    FOREIGN KEY (payee_id) REFERENCES users(id)
);