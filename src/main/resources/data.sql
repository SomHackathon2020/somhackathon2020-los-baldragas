--Users
INSERT INTO USER (dni, password, name, surnames, birthday, mail, phone, address, availability, senior) VALUES ('45263112A', '{noop}password', 'Pep', 'Mogas',NULL,'pepmogas@mataro.com','666555444','Cami Ral 123','Tardes','1');
INSERT INTO USER (dni, password, name, surnames, birthday, mail, phone, address, availability, senior) VALUES ('33363112W', '{noop}password', 'Laia', 'Arquera',NULL,'laia@mataro.com','677111687','Plaça Ajuntament 1','Matís','0');


--Categories
INSERT INTO CATEGORY(name, description) VALUES ('Costura','Descripción de costura');


--Requests
INSERT INTO REQUEST(user_dni, id, description) VALUES ('45263112A',NULL,'Me gustaría encontrar compañía y que me ayuden a hacer la compra. Puedo enseñar a coser.');
INSERT INTO REQUEST(user_dni, id, description) VALUES ('33363112W',NULL,'Me ofrezco para ayudar a personas mayores en su día a día y hacerles sentir mejor :)');