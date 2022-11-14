-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','$2a$12$6nmiHvQd3bKsk3A7CyNUv.aQ7VdYS5wE37u7p48xD0vQbZacr5Cy6',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
-- INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
-- INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');

-- One person user, named ´person1 with passwor personi
INSERT INTO users(username,password,enabled) VALUES ('person1','$2a$12$IrAKbftOfDwUYLPgmItIrOKoTX8flhx3NJOuitiaY/DB4QtL3PwFa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'person1','persona');
-- One person user, named ´person2 with passwor personi
INSERT INTO users(username,password,enabled) VALUES ('person2','$2a$12$IrAKbftOfDwUYLPgmItIrOKoTX8flhx3NJOuitiaY/DB4QtL3PwFa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'person2','persona');

INSERT INTO users(username,password,enabled) VALUES ('person3','$2a$12$IrAKbftOfDwUYLPgmItIrOKoTX8flhx3NJOuitiaY/DB4QtL3PwFa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'person3','persona');

INSERT INTO users(username,password,enabled) VALUES ('person4','$2a$12$IrAKbftOfDwUYLPgmItIrOKoTX8flhx3NJOuitiaY/DB4QtL3PwFa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'person4','persona');

INSERT INTO users(username,password,enabled) VALUES ('person5','$2a$12$IrAKbftOfDwUYLPgmItIrOKoTX8flhx3NJOuitiaY/DB4QtL3PwFa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'person5','persona');

INSERT INTO users(username,password,enabled) VALUES ('person6','$2a$12$IrAKbftOfDwUYLPgmItIrOKoTX8flhx3NJOuitiaY/DB4QtL3PwFa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'person6','persona');

INSERT INTO users(username,password,enabled) VALUES ('person7','$2a$12$IrAKbftOfDwUYLPgmItIrOKoTX8flhx3NJOuitiaY/DB4QtL3PwFa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'person7','persona');

INSERT INTO users(username,password,enabled) VALUES ('person8','$2a$12$IrAKbftOfDwUYLPgmItIrOKoTX8flhx3NJOuitiaY/DB4QtL3PwFa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'person8','persona');

INSERT INTO users(username,password,enabled) VALUES ('person9','$2a$12$IrAKbftOfDwUYLPgmItIrOKoTX8flhx3NJOuitiaY/DB4QtL3PwFa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'person9','persona');

INSERT INTO users(username,password,enabled) VALUES ('person10','$2a$12$IrAKbftOfDwUYLPgmItIrOKoTX8flhx3NJOuitiaY/DB4QtL3PwFa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'person10','persona');

INSERT INTO users(username,password,enabled) VALUES ('person11','$2a$12$IrAKbftOfDwUYLPgmItIrOKoTX8flhx3NJOuitiaY/DB4QtL3PwFa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'person11','persona');

INSERT INTO users(username,password,enabled) VALUES ('person12','$2a$12$IrAKbftOfDwUYLPgmItIrOKoTX8flhx3NJOuitiaY/DB4QtL3PwFa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'person12','persona');

INSERT INTO users(username,password,enabled) VALUES ('user','$2a$12$IrAKbftOfDwUYLPgmItIrOKoTX8flhx3NJOuitiaY/DB4QtL3PwFa',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (16,'user','persona');

INSERT INTO personas(id,first_name,last_name,user) VALUES (1, 'luis', 'chacon romero', 'person1');
INSERT INTO personas(id,first_name,last_name,user) VALUES (2, 'pepe', 'sanchez cortes', 'person2');
INSERT INTO personas(id,first_name,last_name,user) VALUES (3, 'juanan', 'chacon vargas', 'person3');
INSERT INTO personas(id,first_name,last_name,user) VALUES (4, 'jose', 'mena cortes', 'person4');
INSERT INTO personas(id,first_name,last_name,user) VALUES (5, 'eloy', 'moreno romero', 'person5');
INSERT INTO personas(id,first_name,last_name,user) VALUES (6, 'arturo', 'sanchez moral', 'person6');
INSERT INTO personas(id,first_name,last_name,user) VALUES (7, 'lucia', 'fernandez romero', 'person7');
INSERT INTO personas(id,first_name,last_name,user) VALUES (8, 'marta', 'quijada cortes', 'person8');

INSERT INTO personas(id,first_name,last_name,user) VALUES (9, 'prueba', 'prueba persona', 'user');



INSERT INTO boards(id,background,height,width) VALUES (1,'/resources/images/tablero.png',300,500);
INSERT INTO boards(id,background,height,width) VALUES (2,'/resources/images/tablero.png',300,500);
INSERT INTO boards(id,background,height,width) VALUES (3,'/resources/images/tablero.png',300,500);


INSERT INTO games (id,points_black,points_red,board_id,turno,token) VALUES (1,2,2,1,15,'abc-abc');
INSERT INTO games (id,points_black,points_red,board_id,turno,token) VALUES (2,8,4,2,37,'ebc-qer');
INSERT INTO games (id,points_black,points_red,board_id,turno,token) VALUES (3,8,4,3,9,'ebc-qer');


INSERT INTO invitacion_amigo(id, username1, username2) VALUES (1, 'person8', 'person9');


INSERT INTO jugador(id,color,game_id,persona_id) VALUES (1,'red',1,1);
INSERT INTO jugador(id,color,game_id,persona_id) VALUES (2,'black',1,2);


INSERT INTO jugador(id,color,game_id,persona_id) VALUES (3,'red',2,1);
INSERT INTO jugador(id,color,game_id,persona_id) VALUES (4,'black',2,2);

INSERT INTO jugador(id,color,game_id,persona_id) VALUES (5,'black',1,9);


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

INSERT INTO piece(id,color,position,type,board_id) VALUES (27,'black',3,'bacterium',3);

INSERT INTO sarcine(id,color,position,board_id) VALUES (1,'black',1,1);
INSERT INTO sarcine(id,color,position,board_id) VALUES (2,'red',6,1);

INSERT INTO chats(id,text,persona_id, game_id) VALUES (1,'hola1',1,1);
INSERT INTO chats(id,text,persona_id, game_id) VALUES (2,'hola2',2,1);
INSERT INTO chats(id,text,persona_id, game_id) VALUES (3,'hola3',1,1);










