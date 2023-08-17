CREATE DATABASE library;

CREATE TABLE book(
   book_id VARCHAR(16) PRIMARY KEY  NOT NULL,
   book_name  VARCHAR(255)  NOT NULL,
   book_author VARCHAR(50) NOT NULL,
   produce_year VARCHAR(50) NOT NULL,
   book_type VARCHAR(50) NOT NULL,
   category VARCHAR(16) NOT NULL,
   file_url VARCHAR(255),
   image_url VARCHAR(255),
	content bytea
);

CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
	phone_no  VARCHAR(20)
);

CREATE TABLE users_roles (
    user_id BIGINT REFERENCES users(id),
    role_id BIGINT REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);