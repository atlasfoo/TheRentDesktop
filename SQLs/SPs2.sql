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