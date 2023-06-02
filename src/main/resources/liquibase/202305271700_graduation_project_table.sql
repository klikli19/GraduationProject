-- liquibase formatted sql
-- changeset MGubina:1
CREATE TABLE ads(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    description     TEXT,
    price           INTEGER,
    title           TEXT,
    user_id         BIGINT REFERENCES users(id),
    image_id        BIGINT REFERENCES image(id)
);