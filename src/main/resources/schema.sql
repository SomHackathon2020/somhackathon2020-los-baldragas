CREATE TABLE category (
                           name        VARCHAR2(150) NOT NULL,
                           description   VARCHAR2(500)
);

ALTER TABLE category ADD CONSTRAINT category_pk PRIMARY KEY ( name );

CREATE TABLE request (
                          user_dni      VARCHAR2(9) NOT NULL,
                          id            bigint auto_increment, --NOT NULL,
                          state         VARCHAR2(50),
                          description   VARCHAR2(500)
);



CREATE TABLE user (
                         dni               VARCHAR2(9) NOT NULL,
                         password          VARCHAR2(200) NOT NULL,
                         name            VARCHAR2(30) NOT NULL,
                         surnames         VARCHAR2(100) NOT NULL,
                         birthday   DATE NOT NULL,
                         mail              VARCHAR2(50) NOT NULL,
                         phone          VARCHAR2(9) NOT NULL,
                         address         VARCHAR2(150) NOT NULL,
                         availability    VARCHAR2(100) NOT NULL,
                         senior            VARCHAR2(1) NOT NULL
);

ALTER TABLE user ADD CONSTRAINT user_pk PRIMARY KEY ( dni );

CREATE TABLE user_category (
                                   user_dni        VARCHAR2(9) NOT NULL,
                                   category_name   VARCHAR2(150) NOT NULL
);

ALTER TABLE user_category ADD CONSTRAINT user_category_pk PRIMARY KEY ( user_dni,
                                                                                category_name );

CREATE TABLE link (
                         user_dni_senior   VARCHAR2(9) NOT NULL,
                         user_dni_junior    VARCHAR2(9) NOT NULL,
                         date          DATE NOT NULL,
                         assessment     VARCHAR2(30),
                         comment     VARCHAR2(500)
);

ALTER TABLE link
    ADD CONSTRAINT link_pk PRIMARY KEY ( user_dni_senior,
                                            user_dni_junior,
                                            date );

ALTER TABLE request
    ADD CONSTRAINT request_user_fk FOREIGN KEY ( user_dni )
        REFERENCES user ( dni );

ALTER TABLE user_category
    ADD CONSTRAINT user_category_category_fk FOREIGN KEY ( category_name )
        REFERENCES category ( name );

ALTER TABLE user_category
    ADD CONSTRAINT user_category_user_fk FOREIGN KEY ( user_dni )
        REFERENCES user ( dni );

ALTER TABLE link
    ADD CONSTRAINT link_user_fk FOREIGN KEY ( user_dni_junior )
        REFERENCES user ( dni );

ALTER TABLE link
    ADD CONSTRAINT link_user_fkv2 FOREIGN KEY ( user_dni_senior )
        REFERENCES user ( dni );


