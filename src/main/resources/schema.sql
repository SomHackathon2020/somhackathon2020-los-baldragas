CREATE TABLE categoria (
                           nombre        VARCHAR2(150) NOT NULL,
                           descripcion   VARCHAR2(500)
);

ALTER TABLE categoria ADD CONSTRAINT categoria_pk PRIMARY KEY ( nombre );

CREATE TABLE peticion (
                          user_dni   VARCHAR2(9) NOT NULL,
                          id            INTEGER NOT NULL,
                          descripcion   VARCHAR2(500)
);

CREATE TABLE user (
                         dni               VARCHAR2(9) NOT NULL,
                         password          VARCHAR2(200) NOT NULL,
                         name            VARCHAR2(30) NOT NULL,
                         surnames         VARCHAR2(100) NOT NULL,
                         birthday   DATE, --NOT NULL,
                         mail              VARCHAR2(50) NOT NULL,
                         phone          VARCHAR2(9) NOT NULL,
                         address         VARCHAR2(150) NOT NULL,
                         availability    VARCHAR2(100) NOT NULL,
                         senior            VARCHAR2(1) NOT NULL
);

ALTER TABLE user ADD CONSTRAINT user_pk PRIMARY KEY ( dni );

CREATE TABLE user_categoria (
                                   user_dni        VARCHAR2(9) NOT NULL,
                                   categoria_nombre   VARCHAR2(150) NOT NULL
);

ALTER TABLE user_categoria ADD CONSTRAINT user_categoria_pk PRIMARY KEY ( user_dni,
                                                                                categoria_nombre );

CREATE TABLE vinculo (
                         user_dni1   VARCHAR2(9) NOT NULL,
                         user_dni    VARCHAR2(9) NOT NULL,
                         fecha          DATE NOT NULL,
                         valoracion     VARCHAR2(30),
                         comentario     VARCHAR2(500)
);

ALTER TABLE vinculo
    ADD CONSTRAINT vinculo_pk PRIMARY KEY ( user_dni1,
                                            user_dni,
                                            fecha );

ALTER TABLE peticion
    ADD CONSTRAINT peticion_user_fk FOREIGN KEY ( user_dni )
        REFERENCES user ( dni );

ALTER TABLE user_categoria
    ADD CONSTRAINT user_categoria_categoria_fk FOREIGN KEY ( categoria_nombre )
        REFERENCES categoria ( nombre );

ALTER TABLE user_categoria
    ADD CONSTRAINT user_categoria_user_fk FOREIGN KEY ( user_dni )
        REFERENCES user ( dni );

ALTER TABLE vinculo
    ADD CONSTRAINT vinculo_user_fk FOREIGN KEY ( user_dni )
        REFERENCES user ( dni );

ALTER TABLE vinculo
    ADD CONSTRAINT vinculo_user_fkv2 FOREIGN KEY ( user_dni1 )
        REFERENCES user ( dni );


