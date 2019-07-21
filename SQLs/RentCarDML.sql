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


#categorias
insert into Categoria(Nombre, Descripcion, Costo_dia) values('A', 'City Car', 25);
insert into Categoria(Nombre, Descripcion, Costo_dia) values('B', 'Subcompacto', 30);
insert into Categoria(Nombre, Descripcion, Costo_dia) values('C', 'Compacto', 30);
insert into Categoria(Nombre, Descripcion, Costo_dia) values('D', 'Mediano', 40);
insert into Categoria(Nombre, Descripcion, Costo_dia) values('Pick Up E', 'Camioneta con tina eq. básica', 70);
insert into Categoria(Nombre, Descripcion, Costo_dia) values('Pick Up S', 'Camioneta con tina eq. completo', 80);
insert into Categoria(Nombre, Descripcion, Costo_dia) values('Camioneta', 'Camioneta cerrada básica', 70);

select * from Categoria;

#modelos
call sp_new_modelo('Toyota', 'Yaris', '1.3 VVTi', 1, 'Sedan', 16);
call sp_new_modelo('Mazda', '2', '1.5l Skyactiv-G', 1, 'Sedan', 16);
call sp_new_modelo('Chevrolet', 'Spark', '1.0 iDSi', 1, 'Hatchback', 15);
call sp_new_modelo('Chevrolet', 'Beat', '1.4 DOHC', 1, 'Sedan', 16);
call sp_new_modelo('Mitsubishi', 'L200', '2.5 TDi', 2, 'PickUp', 19);
call sp_new_modelo('Mitsubishi', 'L200', '2.5 TDi', 2, 'PickUp', 20);
call sp_new_modelo('Toyota', 'Corolla', '1.8 VVTi', 1, 'Sedan', 17);
call sp_new_modelo('Nissan', 'Frontier NP-300', '3.0 TDi', 2, 'PickUp', 19);

#auto
