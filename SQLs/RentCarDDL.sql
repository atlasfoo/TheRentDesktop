create database bemorsa1qiar4u96lent;

use bemorsa1qiar4u96lent;

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
Id_Empleado int not null,
Fecha_Entrega datetime,
Fecha_Recibido datetime,
Costo double
);

create table Empleado
(
Id_Empleado integer primary key not null auto_increment,
Primer_Nombre varchar(20),
Segundo_Nombre varchar(20),
Primer_Apellido varchar(20),
Segundo_Apellido varchar(20),
Dirreccion varchar(100),
Cedula varchar(16),
Puesto varchar(20)
);

create table Auto
(
Id_Auto integer primary key not null auto_increment,
Placa varchar(10),
No_Chasis integer,
Vin varchar(30),
Color varchar(30),
Transmisión varchar(20),
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
Tipo_Carroceria varchar(20),
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
Id_Empleado integer not null,
user_name varchar(30),
contraseña varchar(20),
rol varchar(30) 
);

create table Estado_Entrega(
	Id_Estado_Entrega integer primary key not null auto_increment,
    Id_Detalle_Renta integer not null,
    kilometraje long not null,
    nivel_combustible float not null,
    descripcion_daño varchar(500)
);

create table Estado_Recibido(
	Id_Estado_Recibido integer primary key not null auto_increment,
    Id_Detalle_Renta integer not null,
    kilometraje long not null,
    nivel_combustible float not null,
    descripcion_daño varchar(500)
);

#foreign keys

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
add foreign key(Id_Empleado)
references Empleado(Id_Empleado);

Alter table Detalle_Renta 
add foreign key(Id_Auto)
references Auto(Id_Auto);

Alter table Auto
add foreign key(Id_Modelo)
references Modelo_Auto(Id_Modelo);

Alter table Modelo_Auto
add foreign key(Id_Categoria)
references Categoria(Id_Categoria);

ALTER TABLE sysuser
add foreign key(Id_Empleado)
references Empleado(Id_empleado);

ALTER TABLE Estado_Entrega
add foreign key(Id_Detalle_Renta)
references Detalle_Renta(Id_Detalle_Renta);

ALTER TABLE Estado_Recibido
add foreign key(Id_Detalle_Renta)
references Detalle_Renta(Id_Detalle_Renta);

#constraints

ALTER TABLE Estado_Entrega
add constraint CK_nivel_combustible
check(nivel_combustible<=1 and nivel_combustible>0);
