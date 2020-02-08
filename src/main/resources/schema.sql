CREATE TABLE activities (
                          timestampinitial   TIMESTAMP NOT NULL,
                          timestampend      TIMESTAMP NOT NULL,
                          content           VARCHAR2(30000) NOT NULL,
                          type              VARCHAR2(30) NOT NULL,
                          users_mail        VARCHAR2(50) NOT NULL,
                          id_machine        VARCHAR2(50) NOT NULL
);

ALTER TABLE activities
    ADD CONSTRAINT activities_pk PRIMARY KEY ( users_mail,
                                             timestampinitial,
                                             timestampend );

CREATE TABLE milestones (
                           dateinitial   DATE NOT NULL,
                           dateend       DATE NOT NULL,
                           target        FLOAT NOT NULL,
                           users_mail    VARCHAR2(50) NOT NULL
);

ALTER TABLE milestones
    ADD CONSTRAINT milestones_pk PRIMARY KEY ( dateinitial,
                                              dateend,
                                              users_mail );

CREATE TABLE users (
                       mail       VARCHAR2(50) NOT NULL,
                       password   VARCHAR2(200) NOT NULL,
                       name       VARCHAR2(30) NOT NULL,
                       surnames   VARCHAR2(100) NOT NULL,
                       birthday   DATE NOT NULL,
                       phone      VARCHAR2(9) NOT NULL,
                       address    VARCHAR2(150) NOT NULL,
                       type       VARCHAR2(50) NOT NULL,
                       height     FLOAT,
                       weight     FLOAT
);

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( mail );

ALTER TABLE activities
    ADD CONSTRAINT activities_users_fk FOREIGN KEY ( users_mail )
        REFERENCES users ( mail );

ALTER TABLE milestones
    ADD CONSTRAINT milestones_users_fk FOREIGN KEY ( users_mail )
        REFERENCES users ( mail );
