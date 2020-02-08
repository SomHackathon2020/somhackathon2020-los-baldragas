--Users
INSERT INTO USER (dni, password, name, surnames, birthday, mail, phone, address, availability, senior) VALUES ('45263112A', '{noop}password', 'Pep', 'Mogas','1930-9-7','pepmogas@mataro.com','666555444','Cami Ral 123','Tardes','1');
INSERT INTO USER (dni, password, name, surnames, birthday, mail, phone, address, availability, senior) VALUES ('33363112W', '{noop}password', 'Laia', 'Arquera','1998-3-2','laia@mataro.com','677111687','Plaça Ajuntament 1','Matís','0');
INSERT INTO USER (dni, password, name, surnames, birthday, mail, phone, address, availability, senior) VALUES ('AAAAAAA', '{noop}password', 'Pep', 'Mogas','1930-9-7','pepmogas@mataro.com','666555444','Cami Ral 123','Tardes','1');
INSERT INTO USER (dni, password, name, surnames, birthday, mail, phone, address, availability, senior) VALUES ('BBBBBB', '{noop}password', 'Pep', 'Mogas','1930-9-7','pepmogas@mataro.com','666555444','Cami Ral 123','Tardes','1');

--Categories
INSERT INTO CATEGORY(name, description) VALUES ('Costura','Descripción de costura');

INSERT INTO USER_CATEGORY (user_dni, category_name) VALUES ('45263112A', 'Costura');

--Requests
INSERT INTO REQUEST(user_dni, id, state, description) VALUES ('45263112A',NULL,'Pending','Me gustaría encontrar compañía y que me ayuden a hacer la compra. Puedo enseñar a coser.');
INSERT INTO REQUEST(user_dni, id, state, description) VALUES ('33363112W',NULL,'Pending','Me ofrezco para ayudar a personas mayores en su día a día y hacerles sentir mejor :)');

--Links
INSERT INTO LINK(user_dni_senior, user_dni_junior, assessment, date, comment) VALUES ('45263112A','33363112W',NULL,'2020-1-2',NULL);
