create database Rentacar;

use rentacar;

create table Cliente
(
Id_Cliente integer primary key not null auto_increment,
Primer_Nombre varchar(20),
Segundo_Nombre varchar(20),
Primer_Apellido varchar(20),
Segundo_Apellido varchar(20),
Cedula varchar(20),
Dirreccion varchar(100),
Tipo_Cliente varchar(20),
Estado varchar(20)
);

create table Cliente_Telefono
(
Id_Cliente int not null,
Telefono integer
);

create table Cliente_Email
(
Id_Cliente int not null,
Email varchar(40)
);

create table Renta
(
Id_Renta integer primary key not null auto_increment,
Id_Cliente int not null,
Fecha date,
Estado varchar(20)
);


create table Detalle_Renta
(
Id_Detalle_Renta integer primary key not null auto_increment,
Id_Renta int not null,
Id_Auto int not null,
Id_Conductor int not null,
Fecha_Entrega datetime,
Fecha_Recibido datetime,
Costo double
);

create table Conductor
(
Id_Conductor integer primary key not null auto_increment,
Primer_Nombre varchar(20),
Segundo_Nombre varchar(20),
Primer_Apellido varchar(20),
Segundo_Apellido varchar(20),
Dirreccion varchar(100),
Cedula varchar(20),
Tipo_Licencia varchar(30)
);

create table Auto
(
Id_Auto integer primary key not null auto_increment,
Placa varchar(10),
No_Chasis integer,
Vin varchar(30),
Color varchar(30),
Transmición varchar(20),
Id_Modelo int not null,
Estado varchar(20)
);

create table Modelo_Auto
(
Id_Modelo integer primary key not null auto_increment,
Marca varchar(30),
Modelo varchar(20),
Motorizacion varchar(20),
Combustible varchar(20),
Tipo_Categoria varchar(20),
Id_Categoria int not null
);

create table Categoria
(
Id_Categoria integer primary key not null auto_increment,
Nombre varchar(30),
Descripcion varchar(50),
Costo_Por_Hora double
);

create table sysuser
(
Id_sysuser integer primary key not null auto_increment,
user_name varchar(30),
contraseña varchar(20),
rol varchar(30) 
);

Alter table Cliente_Telefono 
add foreign key(Id_Cliente)
references Cliente(Id_Cliente);

Alter table Cliente_Email 
add foreign key(Id_Cliente)
references Cliente(Id_Cliente);

Alter table Renta 
add foreign key(Id_Cliente)
references Cliente(Id_Cliente);

Alter table Detalle_Renta 
add foreign key(Id_Renta)
references Renta(Id_Renta);

Alter table Detalle_Renta 
add foreign key(Id_Conductor)
references Conductor(Id_Conductor);

Alter table Detalle_Renta 
add foreign key(Id_Auto)
references Auto(Id_Auto);

Alter table Auto
add foreign key(Id_Modelo)
references Modelo_Auto(Id_Modelo);

Alter table Modelo_Auto
add foreign key(Id_Categoria)
references Categoria(Id_Categoria);






