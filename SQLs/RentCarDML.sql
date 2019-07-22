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

select * from Modelo_Auto;
#auto

call sp_new_auto('M255055', '3GNAL2E0K9ES651801', '3GNAL2EK9ES651801', 'Rojo', 1, 1);
call sp_new_auto('M305479', '3GNAL2E0K9ES651801', '3GNAL2EK9ES651801', 'Blanco', 1, 1);
call sp_new_auto('M289877', '3GNAL2E0K9ES651801', '3GNAL2EK9ES651801', 'Blanco', 2, 2);
call sp_new_auto('M315023', '3GNAL2E0K9ES651801', '3GNAL2EK9ES651801', 'Blanco', 1, 4);
call sp_new_auto('M284758', '3GNAL2E0K9ES651801', '3GNAL2EK9ES651801', 'Rojo', 1, 3);
call sp_new_auto('M299701', '3GNAL2E0K9ES651801', '3GNAL2EK9ES651801', 'Azul', 2, 7);
call sp_new_auto('M270040', '3GNAL2E0K9ES651801', '3GNAL2EK9ES651801', 'Blanco', 1, 5);
call sp_new_auto('M299999', '3GNAL2E0K9ES651801', '3GNAL2EK9ES651801', 'Rojo', 2, 6);


select ma.Marca, ma.Modelo, ma.Tipo_Carroceria, a.Placa, a.Color, a.Transmisión, a.Estado from Auto a 
inner join Modelo_Auto ma on a.Id_Modelo=ma.Id_Modelo;

#Renta
/*ejecutar el procedimiento*/
call sp_new_renta(2,'2019-07-21','RESERVADO');
call sp_new_renta(3,'2019-07-22','CANCELADO');
call sp_new_renta(4,'2019-07-23','RESERVADO');
call sp_new_renta(5,'2019-07-24','PAGADO');
call sp_new_renta(6,'2019-07-21','RESERVADO');
call sp_new_renta(7,'2019-07-22','RESERVADO');
call sp_new_renta(8,'2019-07-23','PAGADO');
call sp_new_renta(9,'2019-07-21','CANCELADO');

select * from Renta;

#detalle renta
/*ejecutar procedimiento*/
call sp_new_detalle_renta(1,10,1,'2019-07-23','2019-07-25',3000);
call sp_new_detalle_renta(2,11,1,'2019-07-26','2019-07-28',3000);
call sp_new_detalle_renta(3,12,1,'2019-07-24','2019-07-29',7500);
call sp_new_detalle_renta(4,13,1,'2019-07-26','2019-07-28',3000);
call sp_new_detalle_renta(5,14,1,'2019-07-25','2019-07-27',3000);
call sp_new_detalle_renta(6,15,1,'2019-07-26','2019-07-29',4500);
call sp_new_detalle_renta(7,16,1,'2019-07-25','2019-07-26',1500);
call sp_new_detalle_renta(8,17,1,'2019-07-28','2019-07-29',1500);


select * from Detalle_Renta;


