# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table beneficiario (
  cedula_b                      varchar(9) not null,
  nombre                        varchar(30),
  apellido                      varchar(30),
  direccion                     varchar(50),
  correo                        varchar(255),
  telefono                      varchar(12),
  estatus                       varchar(1),
  constraint pk_beneficiario primary key (cedula_b)
);

create table empleado (
  cedula_b                      varchar(9) not null,
  usuario_cedula_e              varchar(9),
  nombre                        varchar(30),
  apellido                      varchar(30),
  direccion                     varchar(50),
  correo                        varchar(255),
  telefono                      varchar(12),
  estatus                       varchar(1),
  fundacion_cod_fundacion       varchar(9),
  constraint uq_empleado_usuario_cedula_e unique (usuario_cedula_e),
  constraint uq_empleado_fundacion_cod_fundacion unique (fundacion_cod_fundacion),
  constraint pk_empleado primary key (cedula_b),
  foreign key (cedula_b) references usuario (cedula_e) on delete restrict on update restrict,
  foreign key (usuario_cedula_e) references usuario (cedula_e) on delete restrict on update restrict,
  foreign key (fundacion_cod_fundacion) references fundacion (cod_fundacion) on delete restrict on update restrict
);

create table fundacion (
  cod_fundacion                 varchar(9) not null,
  nombre                        varchar(30),
  porc_partida                  double(30) not null,
  direccion                     varchar(50),
  correo                        varchar(255),
  telefono                      varchar(12),
  estatus                       varchar(1),
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

create table usuario (
  cedula_e                      varchar(9) not null,
  contrasenna                   varchar(30),
  estatus                       varchar(1),
  constraint pk_usuario primary key (cedula_e)
);


# --- !Downs

drop table if exists beneficiario;

drop table if exists empleado;

drop table if exists fundacion;

drop table if exists servicio;

drop table if exists usuario;

