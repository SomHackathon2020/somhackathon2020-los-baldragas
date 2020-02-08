--Users
INSERT INTO USERS (mail, password, name, surnames, birthday, phone, address, type, height, weight) VALUES ('pepmogas@mataro.com', '{noop}password', 'Pep', 'Mogas','1930-9-7','666555444','Cami Ral 123','user','170','80');
INSERT INTO USERS (mail, password, name, surnames, birthday, phone, address, type,height,weight) VALUES ('laia@mataro.com', '{noop}password', 'Laia', 'Arquera','1998-3-2','677111687','Plaça Ajuntament 1','metge','165','55');

INSERT INTO ACTIVITIES (timestampinitial, timestampend, content, type, users_mail) values('2016-11-16 06:55:40.11','2016-11-16 06:56:30.11','[
  {
    "Timestamp": "2016-11-16 06:55:40.11",
    "Lat": "41,53152",
    "Long": "2,437138"
  },
  {
    "Timestamp": "2016-11-16 06:55:50.11",
    "Lat": "41,5316",
    "Long": "2,43723325"
  },
  {
    "Timestamp": "2016-11-16 06:56:00.11",
    "Lat": "41,53168",
    "Long": "2,4373285"
  },
  {
    "Timestamp": "2016-11-16 06:56:10.11",
    "Lat": "41,53172",
    "Long": "2,43738"
  },
  {
    "Timestamp": "2016-11-16 06:56:20.11",
    "Lat": "41,53176",
    "Long": "2,43742375"
  },
  {
    "Timestamp": "2016-11-16 06:56:30.11",
    "Lat": "41,53184",
    "Long": "2,437519"
  }
]','walk','pepmogas@mataro.com');
INSERT INTO ACTIVITIES (timestampinitial, timestampend, content, type, users_mail) values('2015-10-15 05:44:30.08','2016-11-16 06:55:49.11','[
  {
    "Timestamp": "2016-11-16 06:55:40.11"
  },
  {
    "Timestamp": "2016-11-16 06:55:41.11"
  },
  {
    "Timestamp": "2016-11-16 06:55:42.11"
  },
  {
    "Timestamp": "2016-11-16 06:55:43.11"
  },
  {
    "Timestamp": "2016-11-16 06:55:44.11"
  },
  {
    "Timestamp": "2016-11-16 06:55:45.11"
  },
  {
    "Timestamp": "2016-11-16 06:55:46.11"
  },
  {
    "Timestamp": "2016-11-16 06:55:47.11"
  },
  {
    "Timestamp": "2016-11-16 06:55:48.11"
  },
  {
    "Timestamp": "2016-11-16 06:55:49.11"
  }
]','rpm','laia@mataro.com');

INSERT INTO MILESTONES (dateinitial, dateend, target, users_mail) values ('2015-10-15','2015-10-15','1.2f','laia@mataro.com');
INSERT INTO MILESTONES (dateinitial, dateend, target, users_mail) values ('2015-10-15','2015-10-15','1.5f','pepmogas@mataro.com');