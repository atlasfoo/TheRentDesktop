ALTER TABLE Detalle_Renta
DROP FOREIGN KEY Id_Empleado;

ALTER TABLE Renta
ADD COLUMN id_empleado int;

ALTER TABLE Renta
ADD FOREIGN KEY(id_empleado) REFERENCES Empleado(Id_Empleado);

UPDATE Renta SET id_empleado=1 WHERE Id_Renta>0;

SELECT * FROM Renta;

#PROCEDIMIENTOS DE RESERVA

#ver todas las rentas
delimiter //
CREATE PROCEDURE sp_renta_all()
begin
	SELECT r.Id_Renta, CONCAT(c.Primer_Nombre, ' ', c.Primer_Apellido) AS cliente,
	CONCAT(e.Primer_Nombre, ' ', e.Primer_Apellido) AS empleado,
	r.Fecha, r.Estado FROM Renta r 
	INNER JOIN Empleado e ON r.id_empleado=e.Id_Empleado
	INNER JOIN Cliente c ON c.Id_Cliente=r.Id_Cliente
	ORDER BY r.Fecha DESC;
END; //

DROP PROCEDURE sp_cliente_ov;

delimiter //
CREATE PROCEDURE sp_cliente_ov(IN Dato VARCHAR(50))
BEGIN
	SELECT Id_Cliente, CONCAT(Primer_Nombre, ' ', Primer_Apellido) AS nombre, Cedula from Cliente c
	 where c.Primer_Nombre like CONCAT(Dato,'%')
	 or  c.Segundo_Nombre like CONCAT(Dato,'%') 
	 or  c.Primer_Apellido like CONCAT(Dato,'%') 
	 or  c.Segundo_Apellido like CONCAT(Dato,'%')
	 or  c.Cedula like CONCAT(Dato,'%');
END //

CALL sp_cliente_ov('');

DROP PROCEDURE sp_new_renta;

delimiter //
CREATE PROCEDURE sp_new_renta(IN idcliente INT, IN fecha DATE, IN idemp int)
BEGIN
	INSERT INTO Renta(Id_Cliente, Fecha, Estado, id_empleado) VALUES(idcliente, fecha, 'RESERVADO', idemp);
END //

/*Detalle*/

delimiter //
CREATE PROCEDURE sp_renta_detail(IN id_renta INT)
BEGIN
	SELECT dr.Id_Detalle_Renta,
	CONCAT(ma.Marca, ' ', ma.Modelo) AS auto,
	a.año, a.Color, a.Placa, dr.Fecha_Entrega, dr.Fecha_Recibo FROM Detalle_Renta dr
	INNER JOIN Auto a ON a.Id_Auto=dr.Id_Auto
	INNER JOIN Modelo_Auto ma ON a.Id_Modelo=ma.Id_Modelo
	WHERE Id_Renta=id_renta;
END //

#procedimientos de entrega


#buscar las rentas
delimiter //
CREATE PROCEDURE sp_renta_search(in dato Varchar(50))
begin
	SELECT r.Id_Renta, 
    CONCAT(c.Primer_Nombre, ' ', c.Primer_Apellido) AS cliente,
	CONCAT(e.Primer_Nombre, ' ', e.Primer_Apellido) AS empleado,
	r.Fecha, 
    r.Estado
	FROM Renta r 
	INNER JOIN Cliente c ON c.Id_Cliente= r.Id_Cliente
    INNER JOIN Empleado e ON r.id_empleado=e.Id_Empleado
	where CONCAT(c.Primer_Nombre, ' ', c.Primer_Apellido) like CONCAT(dato,'%');
END; //

call sp_renta_search('Francisco')

#buscar auto
delimiter //
create procedure sp_auto_search(in dato varchar(50))
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
	a.No_Chasis AS Chasis,
	c.Descripcion as Categoría,
	c.Costo_dia as Precio,
	c.Deposito AS Depsósito,
	a.is_enabled AS Habilitado,
	a.Estado as Estado FROM Auto a 
	INNER JOIN Modelo_Auto ma ON a.Id_Modelo=ma.Id_Modelo
	INNER JOIN Categoria c ON c.Id_Categoria=ma.Id_Categoria
    where ma.Marca like CONCAT(dato,'%')
    or ma.Modelo like CONCAT(dato,'%');
end; //

call sp_auto_search('Mazda');

#visualizacion
call sp_renta_all();
call sp_auto_all();





