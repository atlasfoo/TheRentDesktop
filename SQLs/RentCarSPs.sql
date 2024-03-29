#TODO: procedimientos almacenados y funciones
use bxhla1vg2uaypvnsiqyx;
#nuevo empleado
DELIMITER //
Create PROCEDURE sp_new_empleado (in p_nom varchar(20), in s_nom varchar(20), in p_apll varchar(20), in s_apll varchar(20), in dir varchar(70), in ced varchar(16), in tel varchar(15), in correo varchar(50), out id_empl int)
begin
	insert into Empleado(Primer_Nombre, Segundo_Nombre, Primer_Apellido, Segundo_Apellido, Direccion, Cedula, Telefono, Correo) values(p_nom, s_nom, p_apll, s_apll, dir, ced, tel, correo);
    select Id_Empleado into id_empl from Empleado where Cedula=ced;
end //

alter table sysuser
drop column contraseña;

alter table sysuser
add column pswd varbinary(100);


#usuario de sistema
CALL sp_new_sysuser(3, 'fsandoval', '12345678', 4, @roln);
SELECT @roln;
DELIMITER //
CREATE PROCEDURE sp_new_sysuser (in id_empleado int, in usrname varchar(50), in pswd varchar(100), in rol int, out roln varchar(15))
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
	END    
//

DROP PROCEDURE sp_login_sysuser
DELIMITER //
CREATE PROCEDURE sp_login_sysuser(in usrname varchar(50), in ipswd varchar(100))
begin
	SELECT e.Id_Empleado, e.Primer_Nombre, e.Segundo_Nombre, e.Primer_Apellido,
	e.Segundo_Apellido, s.rol
	from sysuser s 
	INNER JOIN Empleado e ON e.Id_Empleado=s.Id_Empleado
	where user_name=usrname and pswd=aes_encrypt(ipswd, ipswd);
	
end //

SELECT * FROM Empleado

CALL sp_categoria_all();

#categoría
DELIMITER //
CREATE PROCEDURE sp_categoria_all()
BEGIN	
	SELECT Id_Categoria AS ID,
	Nombre AS Categoría,
	Descripcion AS Descripción,
	Costo_dia AS Costo,
	deposito AS Depósito FROM Categoria;
END //
#modelo de auto

DELIMITER //
CREATE PROCEDURE sp_new_modelo(in marca varchar(30), in modelo varchar(20), in mot varchar(20), in comb int, in tipo_carroceria varchar(20), in id_cat int)
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


DELIMITER //
CREATE PROCEDURE sp_model_all()
BEGIN
	SELECT ma.Id_Modelo AS ID,
	ma.Marca AS Marca,
	ma.Modelo AS Modelo,
	c.Nombre AS Categoria,
	c.Costo_dia AS Costo FROM Modelo_Auto ma 
	INNER JOIN Categoria c ON ma.Id_Categoria=c.Id_Categoria;
END	//

DELIMITER //
CREATE PROCEDURE sp_new_auto(in placa varchar(10), IN yr INT , in no_c varchar(30), in vin varchar(30), in color varchar(30), in trans int, in id_mod int)
begin
	declare transm varchar(20);
	if trans=1 then
		set transm='MANUAL';
	elseif trans=2 then
		set transm='AUTOMATICO';
	else
		set transm='MANUAL';
	end if;
    insert into Auto(Placa, Vin, Color, Transmisión, Id_Modelo, Estado, No_Chasis, año, is_enabled) values(placa, vin, color, transm, id_mod, 'DISPONIBLE', no_c, yr, 'SI');
end//


DELIMITER //
CREATE PROCEDURE sp_auto_all()
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
	INNER JOIN Categoria c ON c.Id_Categoria=ma.Id_Categoria;
end //

CALL sp_auto_all

DELIMITER //
CREATE PROCEDURE sp_enable_car(IN id_aut INT)
BEGIN
	IF(SELECT is_enabled FROM Auto WHERE Id_Auto=id_aut)='SI' THEN
		UPDATE Auto SET is_enabled='NO' WHERE Id_Auto=id_aut;
	ELSE 
		UPDATE Auto SET is_enabled='SI' WHERE Id_Auto=id_aut;
	END if;
END //


DELIMITER //

CREATE PROCEDURE sp_edit_auto(IN id INT,IN plac VARCHAR(20), IN col VARCHAR(50))
BEGIN	
	UPDATE Auto SET Placa=plac, Color=col WHERE Id_Auto=id;
END //


#Mantenimiento

DELIMITER //

CREATE PROCEDURE sp_add_mantenimiento(IN f_in DATE, IN f_out DATE, descr VARCHAR(200), IN id_auto INT)
BEGIN
	INSERT INTO Mantenimiento(fecha_inicio, fecha_fin, descripcion, id_auto) VALUES(f_in, f_out, descr, id_auto);
END	//

CALL sp_auto_all();

SELECT * FROM Mantenimiento

CALL sp_add_mantenimiento('20190506', '20190511', 'Cambio de suspensión', 10);

#Cliente

CREATE DEFINER=`uuvywdmg2p2x5tad`@`%` PROCEDURE `sp_new_cliente`(  in primernombre varchar(20),
	in segundonombre varchar(20),
	in primerapellido varchar(20),
	in segundoapellido varchar(20),
	in cedula varchar(20),
	in direccion varchar(100),
	in tipocliente int)
BEGIN
declare tip varchar(20);
		
		if tipocliente=1 then
		 set tip='Convencional';
		
		elseif tipocliente=2 then
		 set tip='Turista';
		
		else 
		 set tip='Ejecutivo';
		
		end if;
		
		insert into Cliente(Primer_Nombre,Segundo_Nombre,Primer_Apellido
		,Segundo_Apellido,Cedula,Dirreccion,Tipo_Cliente,Estado)
		values (primernombre,segundonombre,primerapellido,segundoapellido,cedula,direccion,tip,'Habilitado');
END


CREATE DEFINER=`uuvywdmg2p2x5tad`@`%` PROCEDURE `sp_new_cliente_email`(in idCliente int, in email varchar(40))
BEGIN
	insert into Cliente_Email values (idCliente,email);
END

CREATE DEFINER=`uuvywdmg2p2x5tad`@`%` PROCEDURE `sp_new_cliente_telefono`(in idCliente int, in Numero int(11))
BEGIN
	insert into Cliente_Telefono values(idCliente,Numero);
END

CREATE DEFINER=`uuvywdmg2p2x5tad`@`%` PROCEDURE `sp_buscar_Cliente`(in Dato varchar(20))
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
END


/*procedimiento para popular las tablas*/

/*popular tabla Renta*/
delimiter //
create PROCEDURE sp_new_renta(
in id_client int,
in estados int)
begin
	insert into Renta(Id_Cliente,Fecha,Estado) values(id_client,fecha_2,estado_2);
end //;
	declare combstat varchar(20);
	if estados=1 then
		set combstat = 'RESERVADO';
	elseif estados=2 then
		set combstat = 'CANCELADO';
	elseif estados=3 then
		set combstat = 'PAGADO';
	end if;
    insert into Renta(Id_Cliente,Fecha,Estado) values(id_client,now(),combstat);
end //

/*Popular tabla detalle renta*/
delimiter //
create procedure sp_new_detalle_renta(
in id_car int,
in date_of_delivery date, 
in date_of_receipt date,
in cost double
)
begin
	insert into Detalle_Renta(Id_Renta,Id_Auto,Id_Empleado,Fecha_Entrega,Fecha_Recibo,Costo) 
					   values(id_rent,id_car,id_employees,date_of_delivery,date_of_receipt,cost);
end //;

SET global log_bin_trust_function_creators=1

#autos reservados en fecha
DELIMITER //
CREATE PROCEDURE sp_disponibilidad_auto(IN f_in DATE,IN f_out DATE)
BEGIN
	CREATE TEMPORARY TABLE autos_ocupados
		SELECT dr.Id_Auto AS id_auto FROM Detalle_Renta dr WHERE (dr.Fecha_Recibo>=f_in AND dr.Fecha_Entrega<f_out);
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
	WHERE a.Id_Auto NOT IN(SELECT id_auto FROM autos_ocupados);
	DROP TEMPORARY TABLE autos_ocupados;
END //

SELECT * FROM Detalle_Renta;
CALL sp_disponibilidad_auto('20190724', '20190726');
SELECT @a;
					   values((select count(distinct Id_Renta)from Renta),id_car,(select count(distinct Id_Empleado)from Empleado),date_of_delivery,date_of_receipt,cost);
end //

#proceso para la view
delimiter //
create PROCEDURE sp_new_renta_view(
in id_client int,
in estados varchar(20))
begin
	insert into Renta(Id_Cliente,Fecha,Estado) values(id_client,now(),estados);
end //

#procedimiento de visualizacion de reservas
delimiter //
create procedure sp_visualizacion_reservas()
begin
select
dr.Id_Detalle_Renta as 'Id_Detalle_Renta', 
MA.Marca as 'Marca',
MA.Modelo as 'Modelo',
dr.Fecha_Entrega as 'Fecha_Entrega', 
dr.Fecha_Recibo as 'Fecha_Recibo',
dr.Costo as 'Costo'
from Renta r
inner join Detalle_Renta dr
on dr.Id_Renta = r.Id_Renta
inner join Auto a
on a.Id_Auto = dr.Id_Auto
inner join Modelo_Auto MA
on MA.Id_Modelo = a.Id_Modelo
group by dr.Id_Detalle_Renta
order by dr.Fecha_Entrega asc;
end //;

/*busqueda de las reservas le falta esta en proceso*/
delimiter //
create procedure sp_buscar_reservas(in dato_cliente Varchar(50))
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
end //

/*Procedimiento almacenado para agregar la reserva*/
delimiter //
create procedure sp_agregar_reserva(
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
end //

#visualizar en mi ventana los clientes
delimiter //
create procedure sp_cliente_vista()
begin
select c.Id_Cliente, c.Primer_Nombre , c.Primer_Apellido from Cliente c;
end //


#visualizar en mi ventana los autos
delimiter //
create procedure sp_auto_vista()
begin
select a.Id_Auto, ma.Marca, ma.Modelo from Auto a inner join Modelo_Auto ma on ma.Id_Modelo = a.Id_Modelo;
end //

#eliminar un detalle renta
delimiter //
create procedure sp_delete_detalle_renta(in id int)
begin
delete from Detalle_Renta
where Id_Detalle_Renta = id;
end //

DROP procedure sp_disponibilidad_auto

ALTER TABLE Modelo_Auto
ADD COLUMN cap_combustible INT;

UPDATE Modelo_Auto SET cap_combustible=26 WHERE Id_Modelo>1;

DROP PROCEDURE sp_generar_entrega;

delimiter //

CREATE PROCEDURE sp_generar_entrega(IN id_det INT, IN fec DATE, IN kil MEDIUMTEXT, IN niv FLOAT, IN descr VARCHAR(100), IN tipo_entrega VARCHAR(20), IN id_emp INT)
BEGIN
	INSERT INTO Estado_Entrega(Id_Detalle_Renta, fecha_reg, kilometraje, nivel_combustible, `descripcion_daño`, tipo_estado, id_empleado) VALUES(32, NOW(), 54787, 0.5, 'Todo correcto', 'RECIBO', 1);
	
	SELECT @id_auto:=dr.Id_Auto FROM Detalle_Renta dr WHERE dr.Id_Detalle_Renta=id_det;
	if tipo_entrega='ENTREGA' then
		UPDATE Auto SET Estado='OCUPADO' WHERE Id_Auto=@id_auto;
	ELSE 
		UPDATE Auto SET Estado='DISPONIBLE' WHERE Id_Auto=@id_auto;
	END if;
END //

SELECT * FROM Estado_Entrega;

SELECT * FROM Empleado;
CALL sp_generar_entrega(34, NOW(), 54787, 0.5, 'Todo correcto', 'RECIBO', 1);

DELETE FROM Estado_Entrega WHERE Id_Estado_Entrega>0;

CALL sp_recibido_today(NOW());
