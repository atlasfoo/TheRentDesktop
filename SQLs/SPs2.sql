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

DROP procedure sp_renta_detail;

delimiter //
CREATE PROCEDURE sp_renta_detail(IN id_renta INT)
BEGIN
	SELECT dr.Id_Detalle_Renta,
	CONCAT(ma.Marca, ' ', ma.Modelo) AS auto,
	a.año, a.Color, a.Placa, dr.Fecha_Entrega, dr.Fecha_Recibo FROM Detalle_Renta dr
	INNER JOIN Auto a ON a.Id_Auto=dr.Id_Auto
	INNER JOIN Modelo_Auto ma ON a.Id_Modelo=ma.Id_Modelo
	WHERE dr.Id_Renta=id_renta;
END //


CALL sp_renta_detail(39);

DROP PROCEDURE sp_disponibilidad_auto;

delimiter //

CREATE PROCEDURE sp_disponibilidad_auto(IN f_in DATE, IN f_out date)
BEGIN
	CREATE TEMPORARY TABLE autos_ocupados
		SELECT dr.Id_Auto AS id_auto FROM Detalle_Renta dr WHERE (dr.Fecha_Recibo>=f_in AND dr.Fecha_Entrega<f_out);
	
	insert into autos_ocupados(id_auto) SELECT m.id_auto FROM Mantenimiento m WHERE (m.fecha_fin>=f_in AND m.fecha_inicio<f_out);
	
	SELECT a.Id_Auto as Id_Auto,
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
	c.Deposito AS Depósito,
	a.is_enabled AS Habilitado,
	a.Estado as Estado FROM Auto a 
	INNER JOIN Modelo_Auto ma ON a.Id_Modelo=ma.Id_Modelo
	INNER JOIN Categoria c ON c.Id_Categoria=ma.Id_Categoria
	WHERE a.Id_Auto NOT IN(SELECT id_auto FROM autos_ocupados);
	DROP TEMPORARY TABLE autos_ocupados;
END //


delimiter //
CREATE PROCEDURE sp_insert_rentdetail(IN id_renta INT, IN id_auto INT, IN f_in DATE, IN f_out DATE)
BEGIN
	SELECT @cost:=c.Costo_dia FROM Auto a INNER JOIN Modelo_Auto ma ON a.Id_Modelo=ma.Id_Modelo 
	INNER JOIN Categoria c ON c.Id_Categoria=ma.Id_Categoria WHERE a.Id_Auto=id_auto; 
	INSERT INTO Detalle_Renta(Id_Renta, Id_Auto, Fecha_Entrega, Fecha_Recibo, Costo) VALUES(id_renta, id_auto, f_in, f_out, @cost);
END //

CALL sp_insert_rentdetail(39, 19, '20190830', '20190905');

DROP PROCEDURE sp_edit_rentdetail;

delimiter //
CREATE PROCEDURE sp_edit_rentdetail(IN id_det_rent int, IN id_auto int, IN f_in DATE, IN f_out DATE, OUT valida VARCHAR(20))
BEGIN
	CREATE TEMPORARY TABLE autos_ocupados
		SELECT dr.Id_Auto AS id_auto FROM Detalle_Renta dr WHERE (dr.Fecha_Recibo>=f_in AND dr.Fecha_Entrega<f_out);
	
	insert into autos_ocupados(id_auto) SELECT m.id_auto FROM Mantenimiento m WHERE (m.fecha_fin>=f_in AND m.fecha_inicio<f_out);
	
	if id_auto IN(SELECT * FROM autos_ocupados) then
		SET valida='INVALIDO';
	else
		UPDATE Detalle_Renta SET Fecha_Entrega=f_in, Fecha_Recibo=f_out WHERE Id_Detalle_Renta=id_det_rent;
		SET valida='VALIDO';
	END if;
	DROP TEMPORARY TABLE autos_ocupados;
END //




#auxiliar de auto
delimiter //
CREATE procedure sp_auto_byplaca(IN plac VARCHAR(20))
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
	WHERE a.Placa=plac;
END //

CALL sp_auto_all();

CALL sp_auto_byplaca('M289877');

CALL sp_login_sysuser('asdasdas','aa');

#SI es seguro borrar detalle renta
delimiter //
CREATE PROCEDURE sp_rentdetail_delete(IN id_det INT)
BEGIN
	DELETE FROM Detalle_Renta WHERE Id_Detalle_Renta=id_det;
END //

delimiter //
CREATE PROCEDURE sp_rent_stat_change(IN id_rent INT,IN stat INT)
BEGIN
	DECLARE statn VARCHAR(15);
	SET statn='RESERVADO';
	if stat=1 then
		SET statn='RESERVADO';
	ELSEIF stat=2 then
		SET statn='PAGADO';
	ELSEIF stat=3 then
		SET statn='CANCELADO';
	else
		SET statn='RESERVADO';
	END if;
	UPDATE Renta SET Estado=statn WHERE Id_Renta=id_rent;
END //		

#procedimientos de entrega

DROP PROCEDURE sp_entrega_today;
DROP PROCEDURE sp_recibido_today;

delimiter //
CREATE PROCEDURE sp_entrega_today(IN fec DATE)
BEGIN
	CREATE TEMPORARY TABLE entregados
		SELECT ee.Id_Detalle_Renta FROM Estado_Entrega ee INNER JOIN Detalle_Renta dr ON ee.Id_Detalle_Renta=dr.Id_Detalle_Renta WHERE tipo_estado='ENTREGA';
	
	SELECT dr.Id_Detalle_Renta,
	CONCAT(ma.Marca, ' ', ma.Modelo) AS auto,
	a.año, a.Color, a.Placa, CONCAT(c.Primer_Nombre, ' ' , c.Primer_Apellido) AS cliente
	FROM Detalle_Renta dr
	INNER JOIN Auto a ON a.Id_Auto=dr.Id_Auto
	INNER JOIN Modelo_Auto ma ON a.Id_Modelo=ma.Id_Modelo
	INNER JOIN Renta r ON r.Id_Renta=dr.Id_Renta
	INNER JOIN Cliente c ON c.Id_Cliente=r.Id_Cliente
	WHERE dr.Fecha_Entrega=fec AND dr.Id_Detalle_Renta NOT IN(SELECT * FROM entregados);
	DROP TEMPORARY TABLE entregados;
END //


delimiter //
CREATE PROCEDURE sp_recibido_today(IN fec DATE)
BEGIN
	
	CREATE TEMPORARY TABLE recibidos
		SELECT ee.Id_Detalle_Renta FROM Estado_Entrega ee INNER JOIN Detalle_Renta dr ON ee.Id_Detalle_Renta=dr.Id_Detalle_Renta WHERE tipo_estado='RECIBO';
	

	SELECT dr.Id_Detalle_Renta,
	CONCAT(ma.Marca, ' ', ma.Modelo) AS auto,
	a.año, a.Color, a.Placa, CONCAT(c.Primer_Nombre, ' ' , c.Primer_Apellido) AS cliente
	FROM Detalle_Renta dr
	INNER JOIN Auto a ON a.Id_Auto=dr.Id_Auto
	INNER JOIN Modelo_Auto ma ON a.Id_Modelo=ma.Id_Modelo
	INNER JOIN Renta r ON r.Id_Renta=dr.Id_Renta
	INNER JOIN Cliente c ON c.Id_Cliente=r.Id_Cliente
	WHERE dr.Fecha_Recibo=fec AND dr.Id_Detalle_Renta NOT IN(SELECT * FROM recibidos);
	DROP TEMPORARY TABLE recibidos;
END //

CALL sp_recibido_today('20190828');