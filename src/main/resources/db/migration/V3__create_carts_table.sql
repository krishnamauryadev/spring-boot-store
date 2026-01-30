create table carts
(
    id  BINARY(16) not null primary key,
    created_at DATE default CURDATE() not null
);
