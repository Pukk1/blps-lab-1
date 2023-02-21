CREATE TABLE item_entity
(
    id              BIGSERIAL PRIMARY KEY,
    full_name       VARCHAR(255) NOT NULL,
    in_stock        BOOLEAN      NOT NULL,
    price           INTEGER,
    warranty_period INTEGER,
    country         VARCHAR(255) NOT NULL,
    definition      VARCHAR(255),
    order_id        BIGINT
);

CREATE TABLE specification_entity
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    value   VARCHAR(255) NOT NULL,
    item_id BIGINT
);

ALTER TABLE specification_entity
    ADD CONSTRAINT FK_SPECIFICATION_ON_ITEM FOREIGN KEY (item_id) REFERENCES item_entity (id);
