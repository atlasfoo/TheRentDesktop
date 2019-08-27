package therent.model.reservation;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import therent.model.beans.Auto;
import therent.model.beans.DetalleRenta;
import therent.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleRentaModel {

    //Metodo para ingresar cliente a la base de datos
    public void IngresarDetalleRenta(DetalleRentaModel modeloDetallerenta) throws  Exception
    {
        /*Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_new_detalle_renta(?, ?, ?, ?)}");

        cs.setInt(1,modeloDetallerenta.getId_Auto());
        cs.setDate(2,modeloDetallerenta.getfecha_entrega());
        cs.setDate(3,modeloDetallerenta.getfecha_recibo());
        cs.setDouble(4,modeloDetallerenta.getCosto());
        cs.execute();
        msgerr("Correcto");
        conn.close();*/
    }




    //Metodo para la dsiponibilidad del auto
    public ObservableList<Auto> disponibilidad_auto(Date fecha_entrega, Date fecha_recibo)
    {
        //Declaracion de variables
        ObservableList<Auto> listaAuto;
        //Inicializando el observablelist
        listaAuto = FXCollections.observableArrayList();

        try {
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            CallableStatement cs = conn.prepareCall("{call sp_disponibilidad_auto(?,?)}"
            );
            cs.setDate(1,fecha_entrega);
            cs.setDate(2,fecha_recibo);
            ResultSet resultado = cs.executeQuery();

            while (resultado.next())
            {
                listaAuto.add(new Auto
                        (
                                resultado.getInt("Id_Auto"),
                                resultado.getString("Marca"),
                                resultado.getString("Modelo"),
                                resultado.getString("Color"),
                                resultado.getInt("Año"),
                                resultado.getString("Transmisión"),
                                resultado.getString("Carrocería"),
                                resultado.getString("Combustible"),
                                resultado.getString("Placa"),
                                resultado.getString("VIN"),
                                resultado.getString("Chasis"),
                                resultado.getString("Categoría"),
                                resultado.getFloat("Precio"),
                                resultado.getFloat("Depósito"),
                                resultado.getString("Habilitado"),
                                resultado.getString("Estado")
                        ));
            }
            conn.close();
        }catch (Exception e){}

        return  listaAuto;
    }

    public List<DetalleRenta> Mostrarlasreservas(int id_renta) throws SQLException
    {
        //Declaracion de variables
        List<DetalleRenta> ListaReserva=new ArrayList<>();
        //Inicializando el observablelist
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            CallableStatement cs = conn.prepareCall("{call sp_renta_detail(?)}");
            cs.setInt(1, id_renta);
            ResultSet res = cs.executeQuery();

            while (res.next())
            {
                ListaReserva.add(
                        new DetalleRenta(
                                res.getInt("Id_Detalle_Renta"),
                                res.getString("auto"),
                                res.getInt("año"),
                                res.getString("Color"),
                                res.getString("Placa"),
                                res.getDate("Fecha_Entrega").toLocalDate(),
                                res.getDate("Fecha_Recibo").toLocalDate()
                        )
                );
            }
            conn.close();
            return  ListaReserva;
    }
}
