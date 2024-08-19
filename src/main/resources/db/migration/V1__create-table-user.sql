CREATE Table users(
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    firstName TEXT NOT NULL,
    lastName TEXT NOT NULL,
    cpfCnpj TEXT UNIQUE NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    currentBalance DECIMAL NOT NULL,
    userType TEXT NOT NULL
);