BEGIN TRANSACTION;
INSERT INTO `usuario_empleado` (codusuario,cedulae) VALUES ('049e2c9e-e643-11e9-81b4-2a2ae2dbcce4','1');
INSERT INTO `usuario` (cod_usuario,codtipouser,contrasenna,estatus) VALUES ('049e2c9e-e643-11e9-81b4-2a2ae2dbcce4',NULL,'123','A');
INSERT INTO `tipouser` (cod_tipo_user,tipo_user,estatus) VALUES ('1','Gavilan','A');
INSERT INTO `play_evolutions` (id,hash,applied_at,apply_script,revert_script,state,last_problem) VALUES (1,'a0d75c7711213fc0349119b3bb453842d4931e26',1570147739287,'create table beneficiario (
cedula_b                      varchar(9) not null,
nombre                        varchar(30),
apellido                      varchar(30),
direccion                     varchar(50),
correo                        varchar(255),
telefono                      varchar(12),
estatus                       varchar(1),
constraint pk_beneficiario primary key (cedula_b)
);

create table detallesolicitud (
cod_solicitud                 varchar(9),
cod_servicio                  varchar(9),
costo                         double(10) not null,
foreign key (cod_solicitud) references solicitud (cod_solicitud) on delete restrict on update restrict,
foreign key (cod_servicio) references servicio (cod_servicio) on delete restrict on update restrict
);

create table empleado (
cedula_e                      varchar(9) not null,
nombre                        varchar(30),
apellido                      varchar(30),
direccion                     varchar(50),
correo                        varchar(255),
telefono                      varchar(12),
estatus                       varchar(1),
codfundacion                  varchar(9),
constraint uq_empleado_codfundacion unique (codfundacion),
constraint pk_empleado primary key (cedula_e),
foreign key (codfundacion) references fundacion (cod_fundacion) on delete restrict on update restrict
);

create table fundacion (
cod_fundacion                 varchar(9) not null,
nombre                        varchar(30),
porc_partida                  double(30) not null,
direccion                     varchar(50),
correo                        varchar(255),
telefono                      varchar(12),
estatus                       varchar(1),
constraint uq_fundacion_nombre unique (nombre),
constraint pk_fundacion primary key (cod_fundacion)
);

create table servicio (
cod_servicio                  varchar(9) not null,
cod_fundacion                 varchar(9) not null,
descripcion                   TEXT,
tipo                          varchar(10),
costo                         double(15) not null,
estatus                       varchar(1),
constraint pk_servicio primary key (cod_servicio),
foreign key (cod_fundacion) references fundacion (cod_fundacion) on delete restrict on update restrict
);

create table solicitud (
cod_solicitud                 varchar(9) not null,
cedulae                       varchar(9),
cedulab                       varchar(9),
prioridad                     varchar(5),
monto_presupuesto             double(15) not null,
fecha_registro                timestamp,
motivo_rechazo                varchar(500),
estatus                       varchar(1),
constraint pk_solicitud primary key (cod_solicitud),
foreign key (cedulae) references empleado (cedula_e) on delete restrict on update restrict,
foreign key (cedulab) references beneficiario (cedula_b) on delete restrict on update restrict
);

create table tipouser (
cod_tipo_user                 varchar(8) not null,
tipo_user                     varchar(20),
estatus                       varchar(1),
constraint pk_tipouser primary key (cod_tipo_user)
);

create table usuario (
cod_usuario                   varchar(9) not null,
codtipouser                   varchar(8),
contrasenna                   varchar(30),
estatus                       varchar(1),
constraint uq_usuario_codtipouser unique (codtipouser),
constraint pk_usuario primary key (cod_usuario),
foreign key (codtipouser) references tipouser (cod_tipo_user) on delete restrict on update restrict
);

create table usuario_beneficiario (
codusuario                    varchar(9),
cedulab                       varchar(9),
constraint uq_usuario_beneficiario_codusuario unique (codusuario),
constraint uq_usuario_beneficiario_cedulab unique (cedulab),
foreign key (codusuario) references usuario (cod_usuario) on delete restrict on update restrict,
foreign key (cedulab) references beneficiario (cedula_b) on delete restrict on update restrict
);

create table usuario_empleado (
codusuario                    varchar(9),
cedulae                       varchar(9),
constraint uq_usuario_empleado_codusuario unique (codusuario),
constraint uq_usuario_empleado_cedulae unique (cedulae),
foreign key (codusuario) references usuario (cod_usuario) on delete restrict on update restrict,
foreign key (cedulae) references empleado (cedula_e) on delete restrict on update restrict
);','drop table if exists beneficiario;

drop table if exists detallesolicitud;

drop table if exists empleado;

drop table if exists fundacion;

drop table if exists servicio;

drop table if exists solicitud;

drop table if exists tipouser;

drop table if exists usuario;

drop table if exists usuario_beneficiario;

drop table if exists usuario_empleado;','applied','');
INSERT INTO `fundacion` (cod_fundacion,nombre,porc_partida,direccion,correo,telefono,estatus) VALUES ('2b59afca-e643-11e9-81b4-2a2ae2dbcce4','AS',10.0,'Hola','asd','asd','I'),
 ('475483a3-9597-48cc-9578-51d111c6be6d','Fundacion de niños, niñas y adolescentes',30.0,'Carrera 20 con calle 23','ima@ima.com','0251-6789262','A');
INSERT INTO `empleado` (cedula_e,nombre,apellido,direccion,correo,telefono,estatus,codfundacion) VALUES ('1','Elizabeth','Warren','Barquisimeto','barqrw@gas.com','0241','A','2b59afca-e643-11e9-81b4-2a2ae2dbcce4');
COMMIT;