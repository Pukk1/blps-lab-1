CREATE TABLE address_entity
(
    id          BIGSERIAL PRIMARY KEY,
    country     VARCHAR(255) NOT NULL,
    city        VARCHAR(255) NOT NULL,
    street      VARCHAR(255) NOT NULL,
    home_number INTEGER      NOT NULL,
    flat        INTEGER      NOT NULL,
    comment     VARCHAR(255)
);
CREATE TABLE order_entity
(
    id                BIGSERIAL PRIMARY KEY,
    coast             FLOAT        NOT NULL,
    phone_number      VARCHAR(255) NOT NULL,
    email             VARCHAR(255) NOT NULL,
    address_entity_id BIGINT       NOT NULL
);

ALTER TABLE order_entity
    ADD CONSTRAINT FK_ORDERENTITY_ON_ADDRESSENTITY FOREIGN KEY (address_entity_id) REFERENCES address_entity (id);