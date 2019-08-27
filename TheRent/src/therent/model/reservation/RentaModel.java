package therent.model.reservation;

import therent.model.beans.Renta;
import therent.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentaModel {


    public void IngresarRenta(RentaModel modelorenta) throws  Exception
    {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_new_renta_view(?, ?)}");
        cs.execute();
        conn.close();
    }

    //Metodo para mostrar todos las rentas
    public List<Renta> AllRenta() throws Exception
    {
        //Declaracion de variables
        List<Renta> listaRenta=new ArrayList<>();

        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs = conn.prepareCall("{call sp_renta_all()}");
        ResultSet res = cs.executeQuery();

        while (res.next())
        {
            listaRenta.add(new Renta
                    (
                         res.getInt("Id_Renta"),
                         res.getString("cliente"),
                         res.getString("empleado"),
                         res.getDate("Fecha").toLocalDate(),
                         res.getString("Estado")
                    ));
        }
        conn.close();
        return  listaRenta;
    }
}
