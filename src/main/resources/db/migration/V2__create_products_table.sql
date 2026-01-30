create table products
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    category_id TINYINT NOT NULL,
    constraint products_categories_id_fk
        foreign key (category_id) references categories (id) on delete restrict
);