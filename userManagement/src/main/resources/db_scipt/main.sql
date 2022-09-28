CREATE DATABASE user_manager_v1;

USE user_manager_v1;

GRANT ALL PRIVILEGES ON user_manager_v1.* TO 'root'@'localhost' IDENTIFIED BY 'Dheeraj@2001';

CREATE TABLE users(
    user_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (user_id)
);
