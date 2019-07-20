#TODO: populacion y prueba de la base de datos
#(PROCURAR USAR PROCEDIMIENTOS ALMACENADOS)

#empleados
call sp_new_empleado('Jerry', 'Francisco', 'Mejia', 'Roa','None' , '001-150699-1015E', '(505)57747568', 'jf_mejiar547@live.com', @res);
select @res;
#usuarios
call sp_new_sysuser(1, 'Admin', 'admin.123', 1, @rold);
select @rold;

call sp_login_sysuser('dmin', 'admin.123', @rolr);
select @rolr;

