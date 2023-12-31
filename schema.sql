CREATE DATABASE library;

CREATE TABLE category (
    id VARCHAR(16) PRIMARY KEY  NOT NULL,
    category_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE book(
   id BIGSERIAL PRIMARY KEY, 
   book_id VARCHAR(16)   NOT NULL,
   book_name  VARCHAR(255)  NOT NULL,
   book_author VARCHAR(50) NOT NULL,
   produce_year VARCHAR(50) NOT NULL,
   book_type VARCHAR(50) NOT NULL,
   category_id VARCHAR(16) references category(id),
   file_url VARCHAR(255),
   image_url VARCHAR(255),
	content bytea,
	is_available BOOLEAN
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
	phone_no  VARCHAR(60) NOT NULL,
	roles VARCHAR(30) NOT NULL
);


-- CREATE TABLE role (
--     id serial PRIMARY KEY,
--     name varchar(255)
-- );

-- INSERT INTO users_roles VALUES (1,1);

-- CREATE TABLE users_roles (
--     user_id BIGINT,
--     role_id BIGINT,
--     PRIMARY KEY (user_id, role_id),
--     FOREIGN KEY (user_id) REFERENCES users(id),
--     FOREIGN KEY (role_id) REFERENCES role(id)
-- );

CREATE TABLE mybookList (
    book_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (book_id) REFERENCES book(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);


