CREATE DATABASE library;

CREATE TABLE book(
   book_id VARCHAR(16) PRIMARY KEY  NOT NULL,
   book_name  VARCHAR(255)  NOT NULL,
   book_author VARCHAR(50) NOT NULL,
   produce_year VARCHAR(50) NOT NULL,
   book_type VARCHAR(50) NOT NULL,
   category VARCHAR(16) NOT NULL,
   file_url VARCHAR(255),
   image_url VARCHAR(255)
);