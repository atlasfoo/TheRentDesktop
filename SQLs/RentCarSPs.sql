#TODO: procedimientos almacenados y funciones
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

ALTER TABLE sysuser convert to character set utf8mb4
drop procedure sp_login_sysuser;
DELIMITER //
CREATE PROCEDURE sp_login_sysuser(in usrname varchar(50), in ipswd varchar(100), out roln varchar(15))
begin
	SELECT rol into roln from sysuser where user_name=usrname and pswd=aes_encrypt(ipswd, ipswd);
    if roln is null then
		set roln='DENEGADO';
	end if;
end //
