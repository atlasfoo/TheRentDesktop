package therent.model.reservation;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import therent.model.beans.Auto;
import therent.model.beans.DetalleRenta;
import therent.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DetalleRentaModel {

    //Metodo para ingresar cliente a la base de datos
    public void IngresarDetalleRenta(int id_renta, int id_auto, LocalDate f_in, LocalDate f_out) throws SQLException
    {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_insert_rentdetail(?, ?, ?, ?)}");

        cs.setInt(1,id_renta);
        cs.setInt(2,id_auto);
        cs.setDate(3, Date.valueOf(f_in));
        cs.setDate(4, Date.valueOf(f_out));
        cs.execute();
        conn.close();
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
                                res.getDate("Fecha_Entrega").toLocalDate().plusDays(1),
                                res.getDate("Fecha_Recibo").toLocalDate().plusDays(1)
                        )
                );
            }
            conn.close();
            return  ListaReserva;
    }

    public boolean editDetail(int id_det, int id_auto, LocalDate f_in, LocalDate f_out) throws SQLException {
        Connection conn=DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{CALL sp_edit_rentdetail(?,?,?,?,?)}");
        cs.setInt(1, id_det);
        cs.setInt(2,id_auto);
        cs.setDate(3,Date.valueOf(f_in));
        cs.setDate(4, Date.valueOf(f_out));
        cs.registerOutParameter(5, Types.VARCHAR);
        cs.execute();
        String res=cs.getString(5);
        conn.close();
        return res.equals("VALIDO");
    }

    public void deleteDetail(int id_det) throws SQLException {
        Connection conn=DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{CALL sp_rentdetail_delete(?)}");
        cs.setInt(1, id_det);
        cs.execute();
        conn.close();
    }
}
