CREATE TABLE order_item
(
    course_id BIGINT NOT NULL,
    order_id  BIGINT NOT NULL,
    PRIMARY KEY (course_id, order_id)
);

ALTER TABLE order_item
    ADD CONSTRAINT fk_ordite_on_item_entity FOREIGN KEY (course_id) REFERENCES item_entity (id);

ALTER TABLE order_item
    ADD CONSTRAINT fk_ordite_on_order_entity FOREIGN KEY (order_id) REFERENCES order_entity (id);