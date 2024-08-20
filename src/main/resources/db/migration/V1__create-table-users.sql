CREATE TABLE users (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    cpf_cnpj TEXT UNIQUE NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    current_balance DECIMAL NOT NULL,
    user_type TEXT NOT NULL
);