DROP DATABASE IF EXISTS movielib CASCADE;
DROP TABLE IF EXISTS movies CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS actors CASCADE;
DROP TABLE IF EXISTS movies_actor CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS reviews CASCADE;
DROP SEQUENCE IF EXISTS actors_actor_id_seq;
DROP SEQUENCE IF EXISTS categories_category_id_seq;
DROP SEQUENCE IF EXISTS movies_movie_id_seq;
DROP SEQUENCE IF EXISTS reviews_review_id_seq;
DROP SEQUENCE IF EXISTS users_user_id_seq ;

CREATE DATABASE movielib;

CREATE TABLE categories (
    category_id serial PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT uniqe_name UNIQUE (name)
);

CREATE TABLE movies(
    movie_id serial PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL REFERENCES categories(name),
    summary TEXT,
    release_date DATE,
    rating NUMERIC(3, 2),
    director VARCHAR(255)
);

CREATE TABLE actors (
    actor_id serial PRIMARY KEY,
    name VARCHAR(150) NOT NULL
);

CREATE TABLE movies_actor (
    movie_id INT REFERENCES movies (movie_id),
    actor_id INT REFERENCES actors (actor_id),
    PRIMARY KEY (movie_id, actor_id)
);

CREATE TABLE users (
    user_id serial PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE reviews (
    review_id serial PRIMARY KEY,
    user_id INT REFERENCES users (user_id),
    movie_id INT REFERENCES movies (movie_id),
    rating NUMERIC(3, 2) NOT NULL,
    comment TEXT
);