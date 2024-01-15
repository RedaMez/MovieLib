DROP TABLE IF EXISTS movies CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS actors CASCADE;
DROP TABLE IF EXISTS movies_actors CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS reviews CASCADE;
DROP TABLE IF EXISTS movies_reviews CASCADE;
DROP SEQUENCE IF EXISTS actors_actor_id_seq;
DROP SEQUENCE IF EXISTS categories_category_id_seq;
DROP SEQUENCE IF EXISTS movies_movie_id_seq;
DROP SEQUENCE IF EXISTS reviews_review_id_seq;
DROP SEQUENCE IF EXISTS users_user_id_seq;

-- Table categories
CREATE TABLE categories (
    category_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Table actors
CREATE TABLE actors (
    actor_id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL
);

-- Table movies
CREATE TABLE movies (
    movie_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    image_url VARCHAR(2000) NOT NULL,
    category_id INT NOT NULL,
    summary TEXT,
    release_date INT,
    rating NUMERIC(3, 2),
    director VARCHAR(255),
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

-- Table movies_actors (for many-to-many relationship)
CREATE TABLE movies_actors (
    movie_id INT REFERENCES movies(movie_id),
    actor_id INT REFERENCES actors(actor_id),
    PRIMARY KEY (movie_id, actor_id)
);

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE roles (
   role_id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users_roles (
   user_id INT REFERENCES users(user_id),
   role_id INT REFERENCES roles(role_id),
   PRIMARY KEY (user_id, role_id)
);

-- Création de la table des critiques
CREATE TABLE reviews (
    review_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    movie_id INT REFERENCES movies(movie_id),
    rating NUMERIC(3, 2) NOT NULL,
    comment TEXT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_movie FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
);


INSERT INTO users(username, email, password)
    VALUES ('reda', 'reda@email.com', 123);
