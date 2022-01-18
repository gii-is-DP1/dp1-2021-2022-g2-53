-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
-- INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
-- INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
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


INSERT INTO boards(id,background,height,width) VALUES (1,'/resources/images/tablero.jpg',300,500);
INSERT INTO boards(id,background,height,width) VALUES (2,'/resources/images/tablero.jpg',300,500);


INSERT INTO games (id,points_black,points_red,board_id,turno,token) VALUES (1,3,8,1,15,'abc-abc');
INSERT INTO games (id,points_black,points_red,board_id,turno,token) VALUES (2,0,0,2,0,'abc-abc');



INSERT INTO jugador(id,color,game_id,persona_id) VALUES (1,'red',1,1);
INSERT INTO jugador(id,color,game_id,persona_id) VALUES (2,'black',1,2);

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

INSERT INTO piece(id,color,position,type,board_id) VALUES (25,'black',3,'bacterium',2);
INSERT INTO piece(id,color,position,type,board_id) VALUES (26,'red',5,'bacterium',2);

INSERT INTO sarcine(id,color,position,board_id) VALUES (1,'black',1,1);
INSERT INTO sarcine(id,color,position,board_id) VALUES (2,'red',6,1);






