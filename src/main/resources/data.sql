--Users
INSERT INTO USER (dni, password, name, surnames, birthday, mail, phone, address, availability, senior) VALUES ('45263112A', '{noop}password', 'Pep', 'Mogas',NULL,'pepmogas@mataro.com','666555444','Cami Ral 123','Tardes','1');

--Categories
INSERT INTO CATEGORY(name, description) VALUES ('Costura','Descripción de costura');

INSERT INTO USER_CATEGORY (user_dni, category_name) VALUES ('45263112A', 'Costura');

--Requests