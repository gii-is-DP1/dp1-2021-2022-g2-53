-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
-- INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
-- INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');

-- One person user, named ´person1 with passwor personi
INSERT INTO users(username,password,enabled) VALUES ('person1','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'person1','persona');
-- One person user, named ´person2 with passwor personi
INSERT INTO users(username,password,enabled) VALUES ('person2','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'person2','persona');

INSERT INTO users(username,password,enabled) VALUES ('person3','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'person3','persona');

INSERT INTO users(username,password,enabled) VALUES ('person4','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'person4','persona');

INSERT INTO users(username,password,enabled) VALUES ('person5','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'person5','persona');

INSERT INTO users(username,password,enabled) VALUES ('person6','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'person6','persona');

INSERT INTO users(username,password,enabled) VALUES ('person7','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'person7','persona');

INSERT INTO users(username,password,enabled) VALUES ('person8','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'person8','persona');

INSERT INTO users(username,password,enabled) VALUES ('person9','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'person9','persona');

INSERT INTO users(username,password,enabled) VALUES ('person10','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'person10','persona');

INSERT INTO users(username,password,enabled) VALUES ('person11','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'person11','persona');

INSERT INTO users(username,password,enabled) VALUES ('person12','personi',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'person12','persona');

INSERT INTO personas VALUES (1, 'luis', 'chacon romero', 'person1');
INSERT INTO personas VALUES (2, 'pepe', 'sanchez cortes', 'person2');
INSERT INTO personas VALUES (3, 'juan', 'mena vargas', 'person3');
INSERT INTO personas VALUES (4, 'antonio', 'chacon romero', 'person4');
INSERT INTO personas VALUES (5, 'pedro', 'sanchez cortes', 'person5');
INSERT INTO personas VALUES (6, 'maria', 'mena vargas', 'person6');
INSERT INTO personas VALUES (7, 'juan', 'chacon romero', 'person7');
INSERT INTO personas VALUES (8, 'mario', 'sanchez cortes', 'person8');
INSERT INTO personas VALUES (9, 'juan', 'perez vargas', 'person9');
INSERT INTO personas VALUES (10, 'luis', 'lopez romero', 'person10');
INSERT INTO personas VALUES (11, 'pepe', 'martin cortes', 'person11');
INSERT INTO personas VALUES (12, 'juan', 'mena rodriguez', 'person12');


INSERT INTO boards(id,background,height,width) VALUES (1,'/resources/images/tablero.png',300,500);
INSERT INTO boards(id,background,height,width) VALUES (2,'/resources/images/tablero.png',300,500);


INSERT INTO games (id,points_black,points_red,board_id,turno,token) VALUES (1,2,2,1,15,'abc-abc');
INSERT INTO games (id,points_black,points_red,board_id,turno,token) VALUES (2,10,4,2,32,'ebc-qer');


INSERT INTO jugador(id,color,game_id,persona_id) VALUES (1,'red',1,1);
INSERT INTO jugador(id,color,game_id,persona_id) VALUES (2,'black',1,2);

INSERT INTO jugador(id,color,game_id,persona_id) VALUES (3,'red',2,1);
INSERT INTO jugador(id,color,game_id,persona_id) VALUES (4,'black',2,2);

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






