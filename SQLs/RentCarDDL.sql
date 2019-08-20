use bemorsa1qiar4u96lent;

#tables
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
Fecha_Entrega date,
Fecha_Recibo date,
Costo double
);

create table Empleado
(
Id_Empleado integer primary key not null auto_increment,
Primer_Nombre varchar(20),
Segundo_Nombre varchar(20),
Primer_Apellido varchar(20),
Segundo_Apellido varchar(20),
Direccion varchar(100),
Cedula varchar(16),
Telefono varchar(15),
Correo varchar(50)
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
Costo_dia float
);

create table Mantenimiento(
	id_mantenimientio integer primary key not null auto_increment,
    id_auto integer not null,
    fecha_inicio date,
    fecha_fin date,
    descripcion varchar(100)
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
    fecha_reg date,
    kilometraje long not null,
    nivel_combustible float not null,
    descripcion_daño varchar(500),
    tipo_estado VARCHAR(20)
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

ALTER TABLE Mantenimiento
add foreign key(id_auto)
references Auto(Id_Auto);

Alter table Modelo_Auto
add foreign key(Id_Categoria)
references Categoria(Id_Categoria);

ALTER TABLE sysuser
add foreign key(Id_Empleado)
references Empleado(Id_empleado);

ALTER TABLE Estado_Entrega
add foreign key(Id_Detalle_Renta)
references Detalle_Renta(Id_Detalle_Renta);

#constraints

ALTER TABLE Cliente
add constraint CK_cliente_stat
check(Estado='ACTIVO' OR Estado='INACTIVO');

ALTER TABLE Renta
add constraint CK_renta_stat
check(Estado='RESERVADO' OR Estado='CANCELADO' OR Estado='PAGADO');

ALTER TABLE Auto
add constraint CK_auto_stat
check(Estado='RENTADO' OR Estado='DISPONIBLE' OR Estado='EN MANTENIMIENTO');

ALTER TABLE sysuser
add constraint CK_sysusr_role
check(rol='SYSADMIN' OR rol='PROPIETARIO' OR rol='VENDEDOR' OR rol='ENTREGA');

ALTER TABLE Estado_Entrega
add constraint CK_entrega_stat
check(tipo_estado='ENTRADA' OR tipo_estado='SALIDA');

ALTER TABLE Modelo_Auto
add constraint CK_combustible
check(Combustible='DIESEL' or Combustible='GASOLINA' or Combustible='HIBRIDO');

ALTER TABLE Auto
add constraint CK_transmision
check(Transmisión='MANUAL' or Transmisión='AUTOMATICO');

ALTER TABLE Estado_Entrega
add constraint CK_nivel_combustible
check(nivel_combustible<=1 and nivel_combustible>0);

alter table Cliente
add constraint CK_Cliente_Cedula
check (Cedula  like '[0-9][0-9][0-9][-][0-9][0-9][0-9][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9][a-z]');

alter table Cliente_Telefono
add constraint CK_Telefono
check (Telefono  like '[0-9][0-9][-][0-9][0-9][0-9][-][0-9][0-9][0-9]');

alter table Cliente
add constraint CK_Tipo_Cliente
check (Tipo_Cliente = 'Convencional' or Tipo_Cliente = 'Turista' or 
Tipo_Cliente = 'Ejecutivo');

alter table Cliente
add constraint CK_Estado
check (Estado = 'Habilitado' or Estado = 'Deshabilitado');