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

DELIMITER //
CREATE PROCEDURE sp_login_sysuser(in usrname varchar(50), in ipswd varchar(100), out roln varchar(15))
begin
	SELECT rol into roln from sysuser where user_name=usrname and pswd=aes_encrypt(ipswd, ipswd);
    if roln is null then
		set roln='DENEGADO';
	end if;
end //

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
in fecha_2 date,
in estado_2 varchar(20))
begin
	insert into Renta(Id_Cliente,Fecha,Estado) values(id_client,fecha_2,estado_2);
end //;

/*Popular tabla detalle renta*/
delimiter //
create procedure sp_new_detalle_renta(
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

DROP procedure sp_disponibilidad_auto

