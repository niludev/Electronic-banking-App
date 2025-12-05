CREATE TABLE IF NOT EXISTS users (
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cards (
    id           BIGSERIAL PRIMARY KEY,
    card_number  VARCHAR(16) UNIQUE NOT NULL,
    bank_name    VARCHAR(50) NOT NULL,
    balance      BIGINT NOT NULL DEFAULT 0,
    user_id      BIGINT NOT NULL,

    CONSTRAINT fk_card_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS transactions (
    id              BIGSERIAL PRIMARY KEY,
    from_card_id    BIGINT NOT NULL,
    to_card_id      BIGINT NOT NULL,
    amount          BIGINT NOT NULL,
    fee             BIGINT NOT NULL,
    type            VARCHAR(20) NOT NULL,
    status          VARCHAR(20) NOT NULL,
    description     TEXT,
    batch_id        BIGINT,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_tx_from_card
    FOREIGN KEY (from_card_id) REFERENCES cards(id),
    CONSTRAINT fk_tx_to_card
    FOREIGN KEY (to_card_id) REFERENCES cards(id)
);
