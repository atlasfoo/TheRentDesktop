package therent.model.reservation;

import therent.model.beans.Renta;
import therent.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentaModel {


    public void IngresarRenta(int id_cl, int id_emp) throws  Exception
    {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_new_renta(?, ?, ?)}");
        cs.setInt(1, id_cl);
        cs.setDate(2, Date.valueOf(LocalDate.now()));
        cs.setInt(3, id_emp);
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
                         res.getDate("Fecha").toLocalDate().plusDays(1),
                         res.getString("Estado")
                    ));
        }
        conn.close();
        return  listaRenta;
    }

    public void changeStat(int id_rent, int stat) throws SQLException {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_rent_stat_change(?, ?)}");
        cs.setInt(1, id_rent);
        cs.setInt(2, stat);
        cs.execute();
        conn.close();
    }
}
