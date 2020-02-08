CREATE TABLE categoria (
                           nombre        VARCHAR2(150) NOT NULL,
                           descripcion   VARCHAR2(500)
);

ALTER TABLE categoria ADD CONSTRAINT categoria_pk PRIMARY KEY ( nombre );

CREATE TABLE peticion (
                          usuario_dni   VARCHAR2(9) NOT NULL,
                          id            INTEGER NOT NULL,
                          descripcion   VARCHAR2(500)
);

CREATE TABLE usuario (
                         dni               VARCHAR2(9) NOT NULL,
                         password          VARCHAR2(200) NOT NULL,
                         nombre            VARCHAR2(30) NOT NULL,
                         apellidos         VARCHAR2(100) NOT NULL,
                         fechanacimiento   DATE, --NOT NULL,
                         mail              VARCHAR2(50) NOT NULL,
                         telefono          VARCHAR2(9) NOT NULL,
                         direccion         VARCHAR2(150) NOT NULL,
                         disponibilidad    VARCHAR2(100) NOT NULL,
                         senior            VARCHAR2(1) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( dni );

CREATE TABLE usuario_categoria (
                                   usuario_dni        VARCHAR2(9) NOT NULL,
                                   categoria_nombre   VARCHAR2(150) NOT NULL
);

ALTER TABLE usuario_categoria ADD CONSTRAINT usuario_categoria_pk PRIMARY KEY ( usuario_dni,
                                                                                categoria_nombre );

CREATE TABLE vinculo (
                         usuario_dni1   VARCHAR2(9) NOT NULL,
                         usuario_dni    VARCHAR2(9) NOT NULL,
                         fecha          DATE NOT NULL,
                         valoracion     VARCHAR2(30),
                         comentario     VARCHAR2(500)
);

ALTER TABLE vinculo
    ADD CONSTRAINT vinculo_pk PRIMARY KEY ( usuario_dni1,
                                            usuario_dni,
                                            fecha );

ALTER TABLE peticion
    ADD CONSTRAINT peticion_usuario_fk FOREIGN KEY ( usuario_dni )
        REFERENCES usuario ( dni );

ALTER TABLE usuario_categoria
    ADD CONSTRAINT usuario_categoria_categoria_fk FOREIGN KEY ( categoria_nombre )
        REFERENCES categoria ( nombre );

ALTER TABLE usuario_categoria
    ADD CONSTRAINT usuario_categoria_usuario_fk FOREIGN KEY ( usuario_dni )
        REFERENCES usuario ( dni );

ALTER TABLE vinculo
    ADD CONSTRAINT vinculo_usuario_fk FOREIGN KEY ( usuario_dni )
        REFERENCES usuario ( dni );

ALTER TABLE vinculo
    ADD CONSTRAINT vinculo_usuario_fkv2 FOREIGN KEY ( usuario_dni1 )
        REFERENCES usuario ( dni );


