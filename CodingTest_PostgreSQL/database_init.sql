DROP TABLE capteur;
DROP TABLE produit;

CREATE TABLE capteur
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE produit
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255),
	quantity int
);

INSERT INTO capteur (name) VALUES ('Capteur de température');
INSERT INTO capteur (name) VALUES ('Capteur d''humidité');

INSERT INTO produit (name, quantity) VALUES ('Lait', 4);
INSERT INTO produit (name, quantity) VALUES ('Jus', 2);
INSERT INTO produit (name, quantity) VALUES ('Champagne', 6);