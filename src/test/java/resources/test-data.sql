DROP TABLE IF EXISTS pokemon, type, region, pokemon_type CASCADE;

START TRANSACTION;
CREATE TABLE pokemon (
    pokemon_id INTEGER PRIMARY KEY,
    name varchar(30),
    hp INTEGER NOT NULL,
    attack INTEGER NOT NULL,
    defense INTEGER NOT NULL,
    sp_attack INTEGER NOT NULL,
    sp_defense INTEGER NOT NULL,
    speed INTEGER NOT NULL,
    total INTEGER NOT NULL
);

CREATE TABLE type (
    type_id SERIAL PRIMARY KEY,
    type_name VARCHAR(20) NOT NULL,
    type_weakness VARCHAR(20) NOT NULL
);

CREATE TABLE region (
    region_id SERIAL PRIMARY KEY,
    region_name VARCHAR(50) NOT NULL,
    map_url VARCHAR(255)
);

CREATE TABLE pokemon_type (
    pokemon_id int NOT NULL,
    type_id int NOT NULL,
    FOREIGN KEY (pokemon_id) REFERENCES pokemon (pokemon_id),
    FOREIGN KEY (type_id) REFERENCES type (type_id)
);

--Region

INSERT INTO region (region_name, map_url) VALUES
('Kanto', 'https://images.app.goo.gl/KyWPVRFkiAsQMeo9A'),
('Johto', 'https://images.app.goo.gl/xhWMqvgqrD5MSp8b7'),
('Hoenn', 'https://images.app.goo.gl/vg9rGpW5Vt7Niozu8'),
('Sinnoh', 'https://images.app.goo.gl/t5aBXZ2o1tANtm3Y6'),
('Unova', 'https://images.app.goo.gl/QRBxdYcGTaHKwiCUA'),
('Kalos', 'https://images.app.goo.gl/gMEADNB7C5Z7ga2j6'),
('Alola', 'https://images.app.goo.gl/Yg8meJbQzJzALPKb7'),
('Galar', 'https://images.app.goo.gl/SRX8acj75d1SqfzY7');


--Type and Weakness
INSERT INTO "type" (type_name, type_weakness) VALUES
('Normal', 'Fighting'),
('Fire', 'Water'),
('Water', 'Electric'),
('Electric', 'Ground'),
('Grass', 'Fire'),
('Ice', 'Fire'),
('Fighting', 'Flying'),
('Poison', 'Ground'),
('Ground', 'Water'),
('Flying', 'Electric'),
('Psychic', 'Bug'),
('Bug', 'Flying'),
('Rock', 'Water'),
('Ghost', 'Ghost'),
('Dragon', 'Ice'),
('Dark', 'Fighting'),
('Steel', 'Fire'),
('Fairy', 'Poison');

--Generation I
INSERT INTO pokemon (pokemon_id, name, hp, attack, defense, sp_attack, sp_defense, speed, total) VALUES
(1, 'Bulbasaur', 45, 49, 49, 65, 65, 45, 318),
(3, 'Venusaur', 80, 82, 83, 100, 100, 80, 525),
(4, 'Charmander', 39, 52, 43, 60, 50, 65, 309),
(5, 'Charmeleon', 58, 64, 58, 80, 65, 80, 405);

ALTER TABLE pokemon
ADD region_id INTEGER,
ADD CONSTRAINT pokemon_fk_region_id FOREIGN KEY(region_id) REFERENCES region(region_id);

UPDATE pokemon
SET region_id = 1
WHERE pokemon_id BETWEEN 1 AND 151;

INSERT INTO pokemon_type (pokemon_id, type_id) VALUES
--Gen I
(1, 5), (1, 8),
(3, 5), (3, 8),
(4, 2),
(5, 2);


COMMIT;





