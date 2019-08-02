-- --------------------------------------------------------
-- Host:                         bemorsa1qiar4u96lent-mysql.services.clever-cloud.com
-- Versión del servidor:         8.0.13-3 - Exherbo
-- SO del servidor:              Linux
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para bemorsa1qiar4u96lent
USE bxhla1vg2uaypvnsiqyx;

-- Volcando estructura para tabla bemorsa1qiar4u96lent.Auto
CREATE TABLE IF NOT EXISTS `Auto` (
  `Id_Auto` int(11) NOT NULL AUTO_INCREMENT,
  `Placa` varchar(10) DEFAULT NULL,
  `Vin` varchar(30) DEFAULT NULL,
  `Color` varchar(30) DEFAULT NULL,
  `Transmisión` varchar(20) DEFAULT NULL,
  `Id_Modelo` int(11) NOT NULL,
  `Estado` varchar(20) DEFAULT NULL,
  `No_Chasis` varchar(30) DEFAULT NULL,
  `año` int(11) DEFAULT NULL,
  `is_enabled` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Auto`),
  KEY `Id_Modelo` (`Id_Modelo`),
  CONSTRAINT `Auto_ibfk_1` FOREIGN KEY (`Id_Modelo`) REFERENCES `Modelo_Auto` (`id_modelo`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bemorsa1qiar4u96lent.Auto: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `Auto` DISABLE KEYS */;
INSERT INTO `Auto` (`Id_Auto`, `Placa`, `Vin`, `Color`, `Transmisión`, `Id_Modelo`, `Estado`, `No_Chasis`, `año`, `is_enabled`) VALUES
	(10, 'M305479', '3GNAL2EK9ES651801', 'Blanco', 'MANUAL', 1, 'DISPONIBLE', '3GNAL2E0K9ES651801', 2017, 'SI'),
	(11, 'M289877', '3GNAL2EK9ES651801', 'Blanco', 'AUTOMATICO', 2, 'DISPONIBLE', '3GNAL2E0K9ES651801', 2017, 'SI'),
	(12, 'M315023', '3GNAL2EK9ES651801', 'Blanco', 'MANUAL', 4, 'DISPONIBLE', '3GNAL2E0K9ES651801', 2017, 'SI'),
	(13, 'M284758', '3GNAL2EK9ES651801', 'Rojo', 'MANUAL', 3, 'DISPONIBLE', '3GNAL2E0K9ES651801', 2017, 'SI'),
	(14, 'M299701', '3GNAL2EK9ES651801', 'Azul', 'AUTOMATICO', 7, 'DISPONIBLE', '3GNAL2E0K9ES651801', 2017, 'SI'),
	(15, 'M270040', '3GNAL2EK9ES651801', 'Blanco', 'MANUAL', 5, 'DISPONIBLE', '3GNAL2E0K9ES651801', 2017, 'SI'),
	(16, 'M299999', '3GNAL2EK9ES651801', 'Rojo', 'AUTOMATICO', 6, 'DISPONIBLE', '3GNAL2E0K9ES651801', 2017, 'SI'),
	(17, 'M255055', '3GNAL2EK9ES651801', 'Rojo', 'MANUAL', 1, 'DISPONIBLE', '3GNAL2E0K9ES651801', 2017, 'SI');
/*!40000 ALTER TABLE `Auto` ENABLE KEYS */;

-- Volcando estructura para tabla bemorsa1qiar4u96lent.Categoria
CREATE TABLE IF NOT EXISTS `Categoria` (
  `Id_Categoria` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(30) DEFAULT NULL,
  `Descripcion` varchar(50) DEFAULT NULL,
  `Costo_dia` float DEFAULT NULL,
  `deposito` float DEFAULT NULL,
  PRIMARY KEY (`Id_Categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bemorsa1qiar4u96lent.Categoria: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `Categoria` DISABLE KEYS */;
INSERT INTO `Categoria` (`Id_Categoria`, `Nombre`, `Descripcion`, `Costo_dia`, `deposito`) VALUES
	(15, 'A', 'City Car', 25, 200),
	(16, 'B', 'Subcompacto', 30, 200),
	(17, 'C', 'Compacto', 30, 200),
	(18, 'D', 'Mediano', 40, 200),
	(19, 'Pick Up E', 'Camioneta con tina eq. básica', 70, 300),
	(20, 'Pick Up S', 'Camioneta con tina eq. completo', 80, 300),
	(21, 'Camioneta', 'Camioneta cerrada básica', 70, 300);
/*!40000 ALTER TABLE `Categoria` ENABLE KEYS */;

-- Volcando estructura para tabla bemorsa1qiar4u96lent.Cliente
CREATE TABLE IF NOT EXISTS `Cliente` (
  `Id_Cliente` int(11) NOT NULL AUTO_INCREMENT,
  `Primer_Nombre` varchar(20) DEFAULT NULL,
  `Segundo_Nombre` varchar(20) DEFAULT NULL,
  `Primer_Apellido` varchar(20) DEFAULT NULL,
  `Segundo_Apellido` varchar(20) DEFAULT NULL,
  `Cedula` varchar(20) DEFAULT NULL,
  `Dirreccion` varchar(100) DEFAULT NULL,
  `Tipo_Cliente` varchar(20) DEFAULT NULL,
  `Estado` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bemorsa1qiar4u96lent.Cliente: ~11 rows (aproximadamente)
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
INSERT INTO `Cliente` (`Id_Cliente`, `Primer_Nombre`, `Segundo_Nombre`, `Primer_Apellido`, `Segundo_Apellido`, `Cedula`, `Dirreccion`, `Tipo_Cliente`, `Estado`) VALUES
	(2, 'Francisco', 'Javier', 'Sandoval', 'Maldonado', '001-061200-1011H', '(Tipitapa) Cohetera San Rafael 1C 1/2 arriba', 'Convencional', 'Habilitado'),
	(3, 'Omar', 'Josue', 'Flores', 'Castillo', '001-132002-1021G', '(Tipitapa) del cementerio central 2c arriba', 'Convencional', 'Habilitado'),
	(4, 'Kevin', 'Alejandro', 'Castro', 'Centeno', '001-182004-1011F', 'VI.Reconciliacion, Semaforos mercado mayoreo 3C', 'Convencional', 'Habilitado'),
	(5, 'Carla', 'Vanessa', 'Rivas', 'Davila', '002-061993-0001J', 'Del Arbolito de Santa Ana 3c Al norte', 'Turista', 'Habilitado'),
	(6, 'Candida', 'Rosa', 'Orozco', 'Vargas', '011-201977-1001C', 'De la rolter km 4.5 2c al sur 1c al norte', 'Convencional', 'Habilitado'),
	(7, 'Betzayda', 'Marbelly', 'Balmaceda', 'Moreno', '011-131997-000M', 'Del gancho de camino 5c al sur 1 al norte', 'Ejecutivo', 'Habilitado'),
	(8, 'Emiliano', 'Francisco', 'Arauz', 'Ortiz', '000-041960-000F', '(Tipitapa) Cohetera leo 1c al sur', 'Turista', 'Habilitado'),
	(9, 'Reyna', 'Marbelly', 'Silva', 'Rojas', '002-041970-001R', 'Del Hospital Aleman, 2C al norte , 1/2c al sur', 'Ejecutivo', 'Habilitado'),
	(10, 'javier', 'francisco', 'maldonado', 'sandoval', '001-061200-1011G', 'nose', 'Convencional', 'Habilitado'),
	(11, 'fdafda', '', 'dasf', 'fdasa', 'afd', 'adf', 'Turista', 'Habilitado'),
	(12, 'fdafda', '', 'dasf', 'fdasa', 'afd', 'adf', 'Turista', 'Habilitado'),
	(13, 'alexander', 'javier', 'flores', 'barreto', '001-061200-1011C', 'nose', 'Ejecutivo', 'Habilitado'),
	(14, 'alexander', 'javier', 'flores', 'barreto', '001-061200-1011C', 'nose', 'Ejecutivo', 'Habilitado'),
	(15, '', '', '', '', '', '', NULL, 'Habilitado');
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;

-- Volcando estructura para tabla bemorsa1qiar4u96lent.Cliente_Email
CREATE TABLE IF NOT EXISTS `Cliente_Email` (
  `Id_Cliente` int(11) NOT NULL,
  `Email` varchar(40) DEFAULT NULL,
  KEY `Id_Cliente` (`Id_Cliente`),
  CONSTRAINT `Cliente_Email_ibfk_1` FOREIGN KEY (`Id_Cliente`) REFERENCES `Cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bemorsa1qiar4u96lent.Cliente_Email: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `Cliente_Email` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cliente_Email` ENABLE KEYS */;

-- Volcando estructura para tabla bemorsa1qiar4u96lent.Cliente_Telefono
CREATE TABLE IF NOT EXISTS `Cliente_Telefono` (
  `Id_Cliente` int(11) NOT NULL,
  `Telefono` int(11) DEFAULT NULL,
  KEY `Id_Cliente` (`Id_Cliente`),
  CONSTRAINT `Cliente_Telefono_ibfk_1` FOREIGN KEY (`Id_Cliente`) REFERENCES `Cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bemorsa1qiar4u96lent.Cliente_Telefono: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `Cliente_Telefono` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cliente_Telefono` ENABLE KEYS */;

-- Volcando estructura para tabla bemorsa1qiar4u96lent.Detalle_Renta
CREATE TABLE IF NOT EXISTS `Detalle_Renta` (
  `Id_Detalle_Renta` int(11) NOT NULL AUTO_INCREMENT,
  `Id_Renta` int(11) NOT NULL,
  `Id_Auto` int(11) NOT NULL,
  `Id_Empleado` int(11) NOT NULL,
  `Fecha_Entrega` date DEFAULT NULL,
  `Fecha_Recibo` date DEFAULT NULL,
  `Costo` double DEFAULT NULL,
  PRIMARY KEY (`Id_Detalle_Renta`),
  KEY `Id_Renta` (`Id_Renta`),
  KEY `Id_Empleado` (`Id_Empleado`),
  KEY `Id_Auto` (`Id_Auto`),
  CONSTRAINT `Detalle_Renta_ibfk_1` FOREIGN KEY (`Id_Renta`) REFERENCES `Renta` (`id_renta`),
  CONSTRAINT `Detalle_Renta_ibfk_2` FOREIGN KEY (`Id_Empleado`) REFERENCES `Empleado` (`id_empleado`),
  CONSTRAINT `Detalle_Renta_ibfk_3` FOREIGN KEY (`Id_Auto`) REFERENCES `Auto` (`id_auto`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bemorsa1qiar4u96lent.Detalle_Renta: ~11 rows (aproximadamente)
/*!40000 ALTER TABLE `Detalle_Renta` DISABLE KEYS */;
INSERT INTO `Detalle_Renta` (`Id_Detalle_Renta`, `Id_Renta`, `Id_Auto`, `Id_Empleado`, `Fecha_Entrega`, `Fecha_Recibo`, `Costo`) VALUES
	(1, 1, 10, 1, '2019-07-23', '2019-07-25', 3000),
	(2, 2, 11, 1, '2019-07-26', '2019-07-28', 3000),
	(3, 3, 12, 1, '2019-07-24', '2019-07-29', 7500),
	(4, 4, 13, 1, '2019-07-26', '2019-07-28', 3000),
	(5, 5, 14, 1, '2019-07-25', '2019-07-27', 3000),
	(6, 6, 15, 1, '2019-07-26', '2019-07-29', 4500),
	(7, 7, 16, 1, '2019-07-25', '2019-07-26', 1500),
	(8, 8, 17, 1, '2019-07-28', '2019-07-29', 1500),
	(9, 9, 12, 1, '2019-07-28', '2019-07-29', 1500),
	(10, 12, 15, 1, '2019-07-27', '2019-07-29', 3000),
	(11, 13, 16, 1, '2019-07-29', '2019-07-31', 3000),
	(12, 14, 11, 1, '2019-07-23', '2019-07-25', 1500);
/*!40000 ALTER TABLE `Detalle_Renta` ENABLE KEYS */;

-- Volcando estructura para tabla bemorsa1qiar4u96lent.Empleado
CREATE TABLE IF NOT EXISTS `Empleado` (
  `Id_Empleado` int(11) NOT NULL AUTO_INCREMENT,
  `Primer_Nombre` varchar(20) DEFAULT NULL,
  `Segundo_Nombre` varchar(20) DEFAULT NULL,
  `Primer_Apellido` varchar(20) DEFAULT NULL,
  `Segundo_Apellido` varchar(20) DEFAULT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  `Cedula` varchar(16) DEFAULT NULL,
  `Telefono` varchar(15) DEFAULT NULL,
  `Correo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id_Empleado`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bemorsa1qiar4u96lent.Empleado: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `Empleado` DISABLE KEYS */;
INSERT INTO `Empleado` (`Id_Empleado`, `Primer_Nombre`, `Segundo_Nombre`, `Primer_Apellido`, `Segundo_Apellido`, `Direccion`, `Cedula`, `Telefono`, `Correo`) VALUES
	(1, 'Jerry', 'Francisco', 'Mejia', 'Roa', 'None', '001-150699-1015E', '(505)57747568', 'jf_mejiar547@live.com');
/*!40000 ALTER TABLE `Empleado` ENABLE KEYS */;

-- Volcando estructura para tabla bemorsa1qiar4u96lent.Estado_Entrega
CREATE TABLE IF NOT EXISTS `Estado_Entrega` (
  `Id_Estado_Entrega` int(11) NOT NULL AUTO_INCREMENT,
  `Id_Detalle_Renta` int(11) NOT NULL,
  `fecha_reg` date DEFAULT NULL,
  `kilometraje` mediumtext NOT NULL,
  `nivel_combustible` float NOT NULL,
  `descripcion_daño` varchar(500) DEFAULT NULL,
  `tipo_estado` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Estado_Entrega`),
  KEY `Id_Detalle_Renta` (`Id_Detalle_Renta`),
  CONSTRAINT `Estado_Entrega_ibfk_1` FOREIGN KEY (`Id_Detalle_Renta`) REFERENCES `Detalle_Renta` (`id_detalle_renta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bemorsa1qiar4u96lent.Estado_Entrega: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `Estado_Entrega` DISABLE KEYS */;
/*!40000 ALTER TABLE `Estado_Entrega` ENABLE KEYS */;

-- Volcando estructura para tabla bemorsa1qiar4u96lent.Mantenimiento
CREATE TABLE IF NOT EXISTS `Mantenimiento` (
  `id_mantenimientio` int(11) NOT NULL AUTO_INCREMENT,
  `id_auto` int(11) NOT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_mantenimientio`),
  KEY `id_auto` (`id_auto`),
  CONSTRAINT `Mantenimiento_ibfk_1` FOREIGN KEY (`id_auto`) REFERENCES `Auto` (`id_auto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bemorsa1qiar4u96lent.Mantenimiento: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `Mantenimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `Mantenimiento` ENABLE KEYS */;

-- Volcando estructura para tabla bemorsa1qiar4u96lent.Modelo_Auto
CREATE TABLE IF NOT EXISTS `Modelo_Auto` (
  `Id_Modelo` int(11) NOT NULL AUTO_INCREMENT,
  `Marca` varchar(30) DEFAULT NULL,
  `Modelo` varchar(20) DEFAULT NULL,
  `Motorizacion` varchar(20) DEFAULT NULL,
  `Combustible` varchar(20) DEFAULT NULL,
  `Tipo_Carroceria` varchar(20) DEFAULT NULL,
  `Id_Categoria` int(11) NOT NULL,
  PRIMARY KEY (`Id_Modelo`),
  KEY `Id_Categoria` (`Id_Categoria`),
  CONSTRAINT `Modelo_Auto_ibfk_1` FOREIGN KEY (`Id_Categoria`) REFERENCES `Categoria` (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bemorsa1qiar4u96lent.Modelo_Auto: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `Modelo_Auto` DISABLE KEYS */;
INSERT INTO `Modelo_Auto` (`Id_Modelo`, `Marca`, `Modelo`, `Motorizacion`, `Combustible`, `Tipo_Carroceria`, `Id_Categoria`) VALUES
	(1, 'Toyota', 'Yaris', '1.3 VVTi', 'GASOLINA', 'Sedan', 16),
	(2, 'Mazda', '2', '1.5l Skyactiv-G', 'GASOLINA', 'Sedan', 16),
	(3, 'Chevrolet', 'Spark', '1.0 iDSi', 'GASOLINA', 'Hatchback', 15),
	(4, 'Chevrolet', 'Beat', '1.4 DOHC', 'GASOLINA', 'Sedan', 16),
	(5, 'Mitsubishi', 'L200', '2.5 TDi', 'DIESEL', 'PickUp', 19),
	(6, 'Mitsubishi', 'L200', '2.5 TDi', 'DIESEL', 'PickUp', 20),
	(7, 'Toyota', 'Corolla', '1.8 VVTi', 'GASOLINA', 'Sedan', 17),
	(8, 'Nissan', 'Frontier NP-300', '3.0 TDi', 'DIESEL', 'PickUp', 19);
/*!40000 ALTER TABLE `Modelo_Auto` ENABLE KEYS */;

-- Volcando estructura para tabla bemorsa1qiar4u96lent.Renta
CREATE TABLE IF NOT EXISTS `Renta` (
  `Id_Renta` int(11) NOT NULL AUTO_INCREMENT,
  `Id_Cliente` int(11) NOT NULL,
  `Fecha` date DEFAULT NULL,
  `Estado` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Renta`),
  KEY `Id_Cliente` (`Id_Cliente`),
  CONSTRAINT `Renta_ibfk_1` FOREIGN KEY (`Id_Cliente`) REFERENCES `Cliente` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bemorsa1qiar4u96lent.Renta: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `Renta` DISABLE KEYS */;
INSERT INTO `Renta` (`Id_Renta`, `Id_Cliente`, `Fecha`, `Estado`) VALUES
	(1, 2, '2019-07-21', 'RESERVADO'),
	(2, 3, '2019-07-22', 'CANCELADO'),
	(3, 4, '2019-07-23', 'RESERVADO'),
	(4, 5, '2019-07-24', 'PAGADO'),
	(5, 6, '2019-07-21', 'RESERVADO'),
	(6, 7, '2019-07-22', 'RESERVADO'),
	(7, 8, '2019-07-23', 'PAGADO'),
	(8, 9, '2019-07-21', 'CANCELADO'),
	(9, 4, '2019-07-25', 'Reservado'),
	(10, 5, '2019-07-25', 'CANCELADO'),
	(11, 6, '2019-07-27', 'CANCELADO'),
	(12, 8, '2019-07-28', 'CANCELADO'),
	(13, 5, '2019-07-28', 'RESERVADO'),
	(14, 7, '2019-07-29', 'CANCELADO');
/*!40000 ALTER TABLE `Renta` ENABLE KEYS */;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_agregar_reserva
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_agregar_reserva`(
/*renta*/
in id_client int,
in estados int,
/*detalle renta*/
in id_car int,
in date_of_delivery date, 
in date_of_receipt date,
in cost double
)
begin
	declare combstat varchar(20);
    if estados=1 then
		set combstat = 'RESERVADO';
	elseif estados=2 then
		set combstat = 'CANCELADO';
	elseif estados=3 then
		set combstat = 'PAGADO';
	end if;
    insert into Renta(Id_Cliente,Fecha,Estado) values(id_client,now(),combstat);
	insert into Detalle_Renta(Id_Renta,Id_Auto,Id_Empleado,Fecha_Entrega,Fecha_Recibo,Costo) 
					   values((select count(distinct Id_Renta)from Renta),id_car,(select count(distinct Id_Empleado)from Empleado),date_of_delivery,date_of_receipt,cost);
end//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_auto_all
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_auto_all`()
begin
	SELECT a.Id_Auto as IDAuto,
	ma.Marca as Marca,
	ma.Modelo as Modelo,
   a.Color as Color,
   a.año AS Año,
	a.Transmisión as Transmisión,
	ma.Tipo_Carroceria as Carrocería,
	ma.Combustible as Combustible,
	a.Placa as Placa,
	a.Vin as VIN,
	c.Descripcion as Categoría,
	c.Costo_dia as Precio,
	c.Deposito AS Depsósito,
	a.is_enabled AS Habilitado,
	a.Estado as Estado FROM Auto a 
	INNER JOIN Modelo_Auto ma ON a.Id_Modelo=ma.Id_Modelo
	INNER JOIN Categoria c ON c.Id_Categoria=ma.Id_Categoria;
end//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_buscar_Cliente
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_buscar_Cliente`(in Dato varchar(20))
BEGIN
 Select
 c.Primer_Nombre,
 c.Segundo_Nombre,
 c.Primer_Apellido,
 c.Segundo_Apellido,
 c.Cedula as Cédula,
 c.Dirreccion as Dirección,
 c.Tipo_Cliente,
 c.Estado as Estado
 from Cliente c
 where c.Primer_Nombre like CONCAT(Dato,'%')
 or  c.Segundo_Nombre like CONCAT(Dato,'%') 
 or  c.Primer_Apellido like CONCAT(Dato,'%') 
 or  c.Segundo_Apellido like CONCAT(Dato,'%')
 or  c.Cedula like CONCAT(Dato,'%');
END//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_buscar_reservas
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_buscar_reservas`(in dato_cliente Varchar(50))
begin
 select 
concat(c.Primer_Nombre , ' ' , c.Segundo_Nombre)  as 'Nombres',
concat(c.Primer_Apellido , ' ' , c.Segundo_Apellido) as 'Apellidos',
dr.Fecha_Entrega as 'Fecha de Entrega', 
dr.Fecha_Recibo as 'Fecha de Recibido',
MA.Marca as 'Marca del Auto',
MA.Modelo as 'Modelo del Auto',
dr.Costo as 'Costo de la Renta'
from Renta r
inner join Detalle_Renta dr
on dr.Id_Renta = r.Id_Renta
inner join Auto a
on a.Id_Auto = dr.Id_Auto
inner join Modelo_Auto MA
on MA.Id_Modelo = a.Id_Modelo
inner join Cliente c
on c.Id_Cliente = r.Id_Cliente
where c.Primer_Nombre like CONCAT(Dato_cliente,'%')
or  c.Segundo_Nombre like CONCAT(Dato_cliente,'%') 
or  c.Primer_Apellido like CONCAT(Dato_cliente,'%') 
or  c.Segundo_Apellido like CONCAT(Dato_cliente,'%');
end//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_login_sysuser
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_login_sysuser`(in usrname varchar(50), in ipswd varchar(100), out roln varchar(15))
begin
	SELECT rol into roln from sysuser where user_name=usrname and pswd=aes_encrypt(ipswd, ipswd);
    if roln is null then
		set roln='DENEGADO';
	end if;
end//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_mostrar_cliente
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_mostrar_cliente`()
BEGIN
select c.Primer_Nombre,c.Segundo_Nombre ,c.Primer_Apellido ,c.Segundo_Apellido,
c.Cedula,
c.Dirreccion,
c.Tipo_Cliente,
c.Estado
from Cliente c
order by Primer_Nombre asc;

END//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_new_auto
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_new_auto`(in placa varchar(10), in no_c varchar(30), in vin varchar(30), in color varchar(30), in trans int, in id_mod int)
begin
	declare transm varchar(20);
	if trans=1 then
		set transm='MANUAL';
	elseif trans=2 then
		set transm='AUTOMATICO';
	else
		set transm='MANUAL';
	end if;
    insert into Auto(Placa, Vin, Color, Transmisión, Id_Modelo, Estado, No_Chasis) values(placa, vin, color, transm, id_mod, 'DISPONIBLE', no_c);
end//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_new_cliente
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_new_cliente`(  in primernombre varchar(20),
	in segundonombre varchar(20),
	in primerapellido varchar(20),
	in segundoapellido varchar(20),
	in cedula varchar(20),
	in direccion varchar(100),
	in tipocliente varchar(20))
BEGIN
		insert into Cliente(Primer_Nombre,Segundo_Nombre,Primer_Apellido
		,Segundo_Apellido,Cedula,Dirreccion,Tipo_Cliente,Estado)
		values (primernombre,segundonombre,primerapellido,segundoapellido,cedula,direccion,tipocliente,'Habilitado');
END//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_new_cliente_email
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_new_cliente_email`(in idCliente int, in email varchar(40))
BEGIN
	insert into Cliente_Email values (idCliente,email);
END//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_new_cliente_telefono
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_new_cliente_telefono`(in idCliente int, in Numero int(11))
BEGIN
	insert into Cliente_Telefono values(idCliente,Numero);
END//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_new_detalle_renta
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_new_detalle_renta`(
in id_rent int, 
in id_car int,
in id_employees int,
in date_of_delivery date, 
in date_of_receipt date,
in cost double
)
begin
	insert into Detalle_Renta(Id_Renta,Id_Auto,Id_Empleado,Fecha_Entrega,Fecha_Recibo,Costo) 
					   values(id_rent,id_car,id_employees,date_of_delivery,date_of_receipt,cost);
end//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_new_empleado
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_new_empleado`(in p_nom varchar(20), in s_nom varchar(20), in p_apll varchar(20), in s_apll varchar(20), in dir varchar(70), in ced varchar(16), in tel varchar(15), in correo varchar(50), out id_empl int)
begin
	insert into Empleado(Primer_Nombre, Segundo_Nombre, Primer_Apellido, Segundo_Apellido, Direccion, Cedula, Telefono, Correo) values(p_nom, s_nom, p_apll, s_apll, dir, ced, tel, correo);
    select Id_Empleado into id_empl from Empleado where Cedula=ced;
end//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_new_modelo
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_new_modelo`(in marca varchar(30), in modelo varchar(20), in mot varchar(20), in comb int, in tipo_carroceria varchar(20), in id_cat int)
begin
	declare combs varchar(20);
	if comb=1 then
		set combs='GASOLINA';
	elseif comb=2 then
		set combs='DIESEL';
	elseif comb=3 then
		set combs='HIBRIDO';
	else
		set combs='GASOLINA';
	end if;
    insert into Modelo_Auto(Marca, Modelo, Motorizacion, Combustible, Tipo_Carroceria, Id_Categoria) values(marca, modelo, mot, combs, tipo_carroceria, id_cat);
end//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_new_renta
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_new_renta`(
in id_client int,
in estados int)
begin
	declare combstat varchar(20);
	if estados=1 then
		set combstat = 'RESERVADO';
	elseif estados=2 then
		set combstat = 'CANCELADO';
	elseif estados=3 then
		set combstat = 'PAGADO';
	end if;
    insert into Renta(Id_Cliente,Fecha,Estado) values(id_client,now(),combstat);
end//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_new_sysuser
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_new_sysuser`(in id_empleado int, in usrname varchar(50), in pswd varchar(100), in rol int, out roln varchar(15))
begin
		declare roln varchar(15);
        
		IF rol=1 THEN
			set roln:='SYSUSER';
		
        elseif rol=2 then
			set roln:='PROPIETARIO';
        
        elseif rol=3 then
			set roln:='VENDEDOR';
        
        else
			set roln:='ENTREGA';
        end if;
        
		insert into sysuser(Id_Empleado, user_name, pswd, rol) VALUES (id_empleado, usrname, aes_encrypt(pswd, pswd), roln);
        
        select rol into roln from sysuser where Id_Empleado=id_empleado;
	END//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_visualizacion_rentas_actuales
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_visualizacion_rentas_actuales`()
create temporary table Fecha_Actual(
IdFecha int primary key not null auto_increment,
Fecha date,
Estado varchar(60));//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_visualizacion_reservas
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_visualizacion_reservas`()
begin
select 
concat(c.Primer_Nombre , ' ' , c.Segundo_Nombre)  as 'Nombres',
concat(c.Primer_Apellido , ' ' , c.Segundo_Apellido) as 'Apellidos',
dr.Fecha_Entrega as 'Fecha de Entrega', 
dr.Fecha_Recibo as 'Fecha de Recibido',
MA.Marca as 'Marca del Auto',
MA.Modelo as 'Modelo del Auto',
dr.Costo as 'Costo de la Renta'
from Renta r
inner join Detalle_Renta dr
on dr.Id_Renta = r.Id_Renta
inner join Auto a
on a.Id_Auto = dr.Id_Auto
inner join Modelo_Auto MA
on MA.Id_Modelo = a.Id_Modelo
inner join Cliente c
on c.Id_Cliente = r.Id_Cliente
group by r.Id_Renta;
end//
DELIMITER ;

-- Volcando estructura para procedimiento bemorsa1qiar4u96lent.sp_visualizacion_reserva_actual
DELIMITER //
CREATE DEFINER=`ujcrxuvtknjzvqqc`@`%` PROCEDURE `sp_visualizacion_reserva_actual`()
begin
	declare fecha_actual date;
    declare contador int;
    declare iterador int ;
    declare dato date;
    set contador = (select count(*) from Detalle_Renta);
    set fecha_actual = now();
    set iterador = 0 ;
	while iterador < contador do 
    set dato = (select dr.Fecha_Entrega from Detalle_Renta dr where dr.Id_Detalle_Renta = iterador);
    if dato >= fecha_actual then
    select
    concat(c.Primer_Nombre , ' ' , c.Segundo_Nombre)  as 'Nombres',
	concat(c.Primer_Apellido , ' ' , c.Segundo_Apellido) as 'Apellidos',
	dr.Fecha_Recibo as 'Fecha de Recibido',
	MA.Marca as 'Marca del Auto',
	MA.Modelo as 'Modelo del Auto',
	dr.Costo as 'Costo de la Renta'
	from Renta r
	inner join Detalle_Renta dr
	on dr.Id_Renta = r.Id_Renta
	inner join Auto a
	on a.Id_Auto = dr.Id_Auto
	inner join Modelo_Auto MA
	on MA.Id_Modelo = a.Id_Modelo
	inner join Cliente c
	on c.Id_Cliente = r.Id_Cliente
    where dr.Id_Detalle_Renta = iterador
	group by r.Id_Renta;
    end if;
	end while;
end//
DELIMITER ;

-- Volcando estructura para tabla bemorsa1qiar4u96lent.sysuser
CREATE TABLE IF NOT EXISTS `sysuser` (
  `Id_sysuser` int(11) NOT NULL AUTO_INCREMENT,
  `Id_Empleado` int(11) NOT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `rol` varchar(30) DEFAULT NULL,
  `pswd` varbinary(100) DEFAULT NULL,
  PRIMARY KEY (`Id_sysuser`),
  KEY `Id_Empleado` (`Id_Empleado`),
  CONSTRAINT `sysuser_ibfk_1` FOREIGN KEY (`Id_Empleado`) REFERENCES `Empleado` (`id_empleado`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bemorsa1qiar4u96lent.sysuser: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `sysuser` DISABLE KEYS */;
INSERT INTO `sysuser` (`Id_sysuser`, `Id_Empleado`, `user_name`, `rol`, `pswd`) VALUES
	(1, 1, 'Admin', 'SYSUSER', _binary 0x571BBFB1F818A7441A9F432C8ED14604);
/*!40000 ALTER TABLE `sysuser` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
