-- liquibase formatted sql

-- changeset klikli:1
CREATE TABLE users
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    phone      VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR DEFAULT 'USER',
    image_id   BIGINT REFERENCES image (id)
)
