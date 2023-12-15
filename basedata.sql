# SQL configs
SET SQL_MODE ='IGNORE_SPACE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

DROP SCHEMA IF EXISTS ecommjava;

# create database and use it
CREATE DATABASE IF NOT EXISTS ecommjava;
USE ecommjava;

SET FOREIGN_KEY_CHECKS=0;

drop table if exists CATEGORY;
drop table if exists PRODUCT;
drop table if exists CUSTOMER;

# create the category table
CREATE TABLE IF NOT EXISTS CATEGORY(
category_id int auto_increment primary key,
name        varchar(255) null
);

# insert default categories
INSERT INTO CATEGORY(name) VALUES ('Fruits'),
                                  ('Vegetables'),
                                  ('Meat'),
                                  ('Fish'),
                                  ('Dairy'),
                                  ('Bakery'),
                                  ('Drinks'),
                                  ('Sweets'),
                                  ('Other');

# create the customer table
CREATE TABLE IF NOT EXISTS CUSTOMER(
id       int auto_increment primary key,
address  varchar(255) null,
email    varchar(255) null,
password varchar(255) null,
role     varchar(255) null,
username varchar(255) null,
UNIQUE (username)
);

# create the product table
CREATE TABLE IF NOT EXISTS PRODUCT(
product_id  int auto_increment primary key,
description varchar(255) null,
image       varchar(255) null,
name        varchar(255) null,
price       int null,
quantity    int null,
weight      int null,
category_id int null,
customer_id int null,
FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id) ON DELETE SET NULL,
FOREIGN KEY (customer_id) REFERENCES CUSTOMER(id) ON DELETE SET NULL
);

# insert default products
INSERT INTO PRODUCT(description, image, name, price, quantity, weight, category_id) VALUES
                                                                                        ('Fresh and juicy', 'https://freepngimg.com/save/9557-apple-fruit-transparent/744x744', 'Apple', 3, 40, 76, 1),
                                                                                        ('Woops! There goes the eggs...', 'https://www.nicepng.com/png/full/813-8132637_poiata-bunicii-cracked-egg.png', 'Cracked Eggs', 1, 90, 43, 9);

create table IF NOT EXISTS cart
(
    id int auto_increment primary key,
    customer_id int not null,
    foreign key (customer_id) references customer(id)
);

create table IF NOT EXISTS cart_product
(
    id int auto_increment primary key,
    cart_id    int not null,
    product_id int not null,
    foreign key (cart_id) references cart(id),
    foreign key (product_id) references product(product_id)
);

# create indexes
CREATE INDEX FK7u438kvwr308xcwr4wbx36uiw
    ON PRODUCT (category_id);

CREATE INDEX FKt23apo8r9s2hse1dkt95ig0w5
    ON PRODUCT (customer_id);

SET FOREIGN_KEY_CHECKS=1;
