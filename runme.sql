INSERT INTO xws_2018_db.country VALUES(1, 'Srbija');
INSERT INTO xws_2018_db.city VALUES(1, 'Gejacka', '22202', 1);

INSERT INTO xws_2018_db.registered_user VALUES(1, 1, '0611586802');
INSERT INTO xws_2018_db.registered_user VALUES(2, 0, '0000000000');
INSERT INTO xws_2018_db.registered_user VALUES(3, 0, '1111111111');
INSERT INTO xws_2018_db.registered_user VALUES(4, 0, '2222225552');
INSERT INTO xws_2018_db.registered_user VALUES(5, 0, '2222225552');
INSERT INTO xws_2018_db.registered_user VALUES(6, 0, '2222225552');

INSERT INTO xws_2018_db.user_roles VALUES (1, 'REG_USER');
INSERT INTO xws_2018_db.user_roles VALUES (2, 'AGENT');
INSERT INTO xws_2018_db.user_roles VALUES (3, 'ADMIN');

INSERT INTO xws_2018_db.user VALUES(1, 'asdf@gmail.com', 'Adresa neka', 'Riki', '$2a$10$jtXXduPiYQXFhNePdc8GmeCQb8r63fnduV9BDHiw6bJnV1WmDAKqG', 'Ribar', 'riki', 1, 3);
INSERT INTO xws_2018_db.user VALUES(2, 'dvojka@gmail.com', 'Ako budem kucao bazu', 'Rikibog', '$2a$10$jtXXduPiYQXFhNePdc8GmeCQb8r63fnduV9BDHiw6bJnV1WmDAKqG', 'Waaa', 'wawa', 1, 1);
INSERT INTO xws_2018_db.user VALUES(3, 'trojka@gmail.com', 'Poludecu', 'RikiHaker', '$2a$10$jtXXduPiYQXFhNePdc8GmeCQb8r63fnduV9BDHiw6bJnV1WmDAKqG', 'Ribar', 'tawa', 1, 1);
INSERT INTO xws_2018_db.user VALUES(4, 'tusekumapf@gmail.com', 'NJENJEEE', 'RikiMartin', '$2a$10$jtXXduPiYQXFhNePdc8GmeCQb8r63fnduV9BDHiw6bJnV1WmDAKqG', 'Zawa', 'zawa', 1, 2);
INSERT INTO xws_2018_db.user VALUES(5, 'aaaa@gmail.com', 'NJEE', 'BrateShvtim', '$2a$10$jtXXduPiYQXFhNePdc8GmeCQb8r63fnduV9BDHiw6bJnV1WmDAKqG', 'Rawa', 'rawa', 1, 2);
INSERT INTO xws_2018_db.user VALUES(6, '2@gmail.com', 'Deleteme', 'BrateS222', '$2a$10$jtXXduPiYQXFhNePdc8GmeCQb8r63fnduV9BDHiw6bJnV1WmDAKqG', 'Cascade', 'deleteMe', 1, 1);

INSERT INTO xws_2018_db.accomodation_category VALUES(1, 'Luxuz bajo', 5);
INSERT INTO xws_2018_db.accomodation_type VALUES(1, 'Akomodacija luksuz bajo');
INSERT INTO xws_2018_db.booking_unit VALUES(1, 'adsf', 'desc', 'Pefkohori', 5, 1,1,4,1);