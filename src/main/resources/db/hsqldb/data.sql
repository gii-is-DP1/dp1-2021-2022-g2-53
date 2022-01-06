-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');
-- One person user, named ´person1 with passwor personi
INSERT INTO users(username,password,enabled) VALUES ('person1','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'person1','persona');
-- One person user, named ´person2 with passwor personi
INSERT INTO users(username,password,enabled) VALUES ('person2','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'person2','persona');

INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO personas VALUES (1, 'luis', 'chacon romero', 'person1');
INSERT INTO personas VALUES (2, 'pepe', 'sanchez cortes', 'person2');


INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (11, 'Luis', 'Chacon', '06011 Valdepasillas', 'Badajoz', '6085555487', 'owner1');
INSERT INTO owners VALUES (12, 'Jose', 'Martin', 'Calle Ejemplo 22', 'Sevilla', '6085555487', 'owner1');
INSERT INTO owners VALUES (13, 'Alvaro', 'Rodriguez', 'Calle Otro Ejemplo 42', 'Sevilla', '6085555489', 'owner1');
INSERT INTO owners VALUES (14, 'Eloy', 'Moreno Dominguez', 'Calle Otro Ejemplo 42', 'Sevilla', '6085555489', 'owner1');
INSERT INTO owners VALUES (15, 'Juan Antonio', 'Mena Vargas', 'Calle Otro Ejemplo 42', 'Sevilla', '6085555489', 'owner1');


INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Perro', '2012-07-08', 1, 11);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Fuwa', '2019-02-03', 1, 13);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');



INSERT INTO boards(id,background,height,width) VALUES (1,'/resources/images/tablero.jpg',300,500);
INSERT INTO boards(id,background,height,width) VALUES (2,'/resources/images/tablero.jpg',300,500);


INSERT INTO games (id,points_black,points_red,board_id,turno,token) VALUES (1,3,8,1,15,'abc-abc');
INSERT INTO games (id,points_black,points_red,board_id,turno,token) VALUES (2,0,0,2,0,'abc-abc');



INSERT INTO jugador(id,color,game_id) VALUES (1,'red',1);

INSERT INTO piece(id,color,position,type,board_id) VALUES (1,'black',1,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (2,'red',2,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (3,'black',3,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (4,'red',4,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (5,'red',5,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (6,'red',6,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (7,'black',7,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (8,'black',1,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (9,'red',1,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (10,'red',2,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (11,'black',3,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (12,'black',3,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (13,'red',7,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (14,'black',4,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (15,'black',4,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (16,'red',5,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (17,'red',5,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (18,'red',5,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (19,'black',6,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (20,'red',6,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (21,'black',7,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (22,'red',7,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (23,'black',7,'bacterium',1);
INSERT INTO piece(id,color,position,type,board_id) VALUES (24,'red',7,'bacterium',1);

INSERT INTO piece(id,color,position,type,board_id) VALUES (25,'black',7,'bacterium',2);
INSERT INTO piece(id,color,position,type,board_id) VALUES (26,'red',7,'bacterium',2);

INSERT INTO sarcine(id,color,position,board_id) VALUES (1,'black',1,1);
INSERT INTO sarcine(id,color,position,board_id) VALUES (2,'red',6,1);






