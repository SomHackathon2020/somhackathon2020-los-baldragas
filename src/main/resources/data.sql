--Users
INSERT INTO USERS (mail, password, name, surnames, birthday, phone, address, type, height, weight) VALUES ('pepmogas@mataro.com', '{noop}password', 'Pep', 'Mogas','1930-9-7','666555444','Cami Ral 123','user','170','80');
INSERT INTO USERS (mail, password, name, surnames, birthday, phone, address, type,height,weight) VALUES ('laia@mataro.com', '{noop}password', 'Laia', 'Arquera','1998-3-2','677111687','Plaça Ajuntament 1','metge','165','55');

INSERT INTO ACTIVITIES (timestampinitial, timestampend, content, type, users_mail) values('2016-11-16 06:55:40.11','2016-11-16 06:55:40.11','Hello','cool','pepmogas@mataro.com');
INSERT INTO ACTIVITIES (timestampinitial, timestampend, content, type, users_mail) values('2015-10-15 05:44:30.08','2015-10-15 05:44:30.10','Bye','hot','laia@mataro.com');