CREATE TABLE customer
(
    id        BIGSERIAL PRIMARY KEY,
    created   BIGINT,
    updated   BIGINT,
    full_name VARCHAR(50) CHECK (LENGTH(full_name) BETWEEN 2 AND 50)      NOT NULL,
    email     VARCHAR(100) CHECK (LENGTH(email) BETWEEN 2 AND 100) UNIQUE NOT NULL,
    phone     VARCHAR(14) CHECK (LENGTH(phone) BETWEEN 6 AND 14),
    is_active BOOLEAN                                                     NOT NULL
);
