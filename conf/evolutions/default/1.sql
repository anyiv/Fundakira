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
  nombre                        varchar(30),
  apellido                      varchar(30),
  direccion                     varchar(50),
  correo                        varchar(255),
  telefono                      varchar(12),
  estatus                       varchar(1),
  constraint pk_empleado primary key (cedula_b)
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
  descripcion                   TEXT,
  tipo                          varchar(10),
  costo                         double(15) not null,
  estatus                       varchar(1),
  constraint pk_servicio primary key (cod_servicio)
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

