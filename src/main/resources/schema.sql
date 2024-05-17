CREATE DATABASE IF NOT EXISTS velocity_service2;

CREATE TABLE IF NOT EXISTS supplier(
    `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(255),
    `phone` bigint(20),
    `email` varchar(30),
    `address` varchar(500),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS product(
    `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(255),
    `stock_qty` bigint(20) DEFAULT 0,
    `supplier_id` bigint(20) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `fk_product_supplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `supplier`(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


