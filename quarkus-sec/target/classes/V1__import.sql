CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);


INSERT INTO users (username, password) VALUES ('user1', 'senha1');
INSERT INTO users (username, password) VALUES ('user2', 'senha2');
