BEGIN TRANSACTION;
INSERT INTO `usuario_empleado` (codusuario,cedulae) VALUES ('049e2c9e-e643-11e9-81b4-2a2ae2dbcce4','1'),
 ('520a04ec-f39b-4f31-8f07-2ffcd95e899b','2'),
 ('c02fc6a7-2c6e-4097-8a3a-bbbc4391f3cd','3');
INSERT INTO `usuario_beneficiario` (codusuario,cedulab) VALUES ('ce523d78-5139-4a79-84dd-509d3aa0ff9a','3'),
 ('9ba68702-8dc5-4f59-9dc6-05a3843c7aba','4');
INSERT INTO `usuario` (cod_usuario,codtipouser,contrasenna,estatus) VALUES ('049e2c9e-e643-11e9-81b4-2a2ae2dbcce4','2','123','A'),
 ('ce523d78-5139-4a79-84dd-509d3aa0ff9a','3','123','A'),
 ('9ba68702-8dc5-4f59-9dc6-05a3843c7aba','3','123','A'),
 ('520a04ec-f39b-4f31-8f07-2ffcd95e899b','2','1234','A'),
 ('3b855334-20b6-43f6-bae1-6771ac148fd3','2','1234','A'),
 ('c02fc6a7-2c6e-4097-8a3a-bbbc4391f3cd','2','1234','A');
INSERT INTO `tipouser` (cod_tipo_user,tipo_user,estatus) VALUES ('1','admin','A'),
 ('2','emp','A'),
 ('3','ben','A');
INSERT INTO `solicitud` (cod_solicitud,cedulae,cedulab,otras_donaciones,razon,prioridad,fecha_registro,motivo_rechazo,estatus) VALUES ('96900bfd-d9ee-4ec4-ada8-acb96d5268f0','2','1',NULL,'','Media',1573999749560,NULL,'P');
INSERT INTO `servicio` (cod_servicio,cod_fundacion,nombre,tipo,costo,estatus) VALUES ('30cb8ea3-4acb-4e4b-b7a3-fa11e7aa04a5','2ef99adf-50f8-4ffb-8937-6cacd828766a','Consulta Oftalmológica','Medico',10000.0,'A');
INSERT INTO `play_evolutions` (id,hash,applied_at,apply_script,revert_script,state,last_problem) VALUES (1,'2f4c8728accf07123e42c996e4a5e6c5009a7a83',1573999623546,'create table beneficiario (
cedula_b                      varchar(9) not null,
nombre_b                      varchar(30),
apellido_b                    varchar(30),
direccion_b                   varchar(50),
correo_b                      varchar(255),
telefono_b                    varchar(12),
estatus_b                     varchar(1),
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
tipo                          varchar(20),
estatus                       varchar(1),
constraint pk_fundacion primary key (cod_fundacion)
);

create table servicio (
cod_servicio                  varchar(9) not null,
cod_fundacion                 varchar(9) not null,
nombre                        varchar(30),
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
otras_donaciones              varchar(25),
razon                         varchar(200),
prioridad                     varchar(5),
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
INSERT INTO `fundacion` (cod_fundacion,nombre,porc_partida,direccion,correo,telefono,tipo,estatus) VALUES ('2b59afca-e643-11e9-81b4-2a2ae2dbcce4','AS',10.0,'Hola','asd','asd',NULL,'I'),
 ('2ef99adf-50f8-4ffb-8937-6cacd828766a','Fundación Regional de la Mujer',30.0,'Calle 43','hola@gmail.com','0251-6789262','Publica','A');
INSERT INTO `empleado` (cedula_e,nombre,apellido,direccion,correo,telefono,estatus,codfundacion) VALUES ('1','Elizabeth','Warren','Barquisimeto','barqrw@gas.com','0241','A','2b59afca-e643-11e9-81b4-2a2ae2dbcce4'),
 ('2','Lisbeth','omy','Poniente','eldany@gmail.com','0251-6789262','A','2ef99adf-50f8-4ffb-8937-6cacd828766a'),
 ('3','Andrea','Mantilla','Carrera 20 con calle 23','fnda@gmail.com','0251-6789262','A','2ef99adf-50f8-4ffb-8937-6cacd828766a');
INSERT INTO `detallesolicitud` (cod_solicitud,cod_servicio,costo) VALUES ('96900bfd-d9ee-4ec4-ada8-acb96d5268f0','30cb8ea3-4acb-4e4b-b7a3-fa11e7aa04a5',10000.0);
INSERT INTO `beneficiario` (cedula_b,nombre_b,apellido_b,direccion_b,correo_b,telefono_b,estatus_b) VALUES ('1','Alan
','Güevara','Maracay','guevara@alan.net','0222','A'),
 ('2','Carlos','Villagran','Villanueva','jose@gmail.com','0424678923','A'),
 ('3','Carlos','Villagran','Venezuela','das@dag.com','0424678923','A'),
 ('4','Juan','Torres','Maracaibo','juan@gmail.com','0414-167882','A');
COMMIT;
