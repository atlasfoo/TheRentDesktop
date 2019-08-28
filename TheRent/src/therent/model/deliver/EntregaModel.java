package therent.model.deliver;

import jdk.nashorn.internal.scripts.JD;
import therent.model.beans.DetalleEntrega;
import therent.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntregaModel {
    public List<DetalleEntrega> entregaToday(LocalDate dat) throws SQLException {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{CALL sp_entrega_today(?)}");
        cs.setDate(1, Date.valueOf(dat));
        ResultSet res = cs.executeQuery();
        List<DetalleEntrega> ListaReserva=new ArrayList<>();
        while (res.next())
        {
            ListaReserva.add(
                    new DetalleEntrega(
                            res.getInt("Id_Detalle_Renta"),
                            res.getString("auto"),
                            res.getInt("año"),
                            res.getString("Color"),
                            res.getString("Placa"),
                            res.getString("cliente")
                    )
            );
        }
        conn.close();
        return ListaReserva;
    }

    public List<DetalleEntrega> reciboToday(LocalDate dat) throws SQLException {
        Connection conn=DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{CALL sp_recibido_today(?)}");
        cs.setDate(1,Date.valueOf(dat));
        ResultSet res = cs.executeQuery();
        List<DetalleEntrega> ListaReserva=new ArrayList<>();
        while (res.next())
        {
            ListaReserva.add(
                    new DetalleEntrega(
                            res.getInt("Id_Detalle_Renta"),
                            res.getString("auto"),
                            res.getInt("año"),
                            res.getString("Color"),
                            res.getString("Placa"),
                            res.getString("cliente")
                    )
            );
        }
        conn.close();
        return ListaReserva;
    }
    public void addEntrega(int id_det, long kil, float comb, String descr, String tipo, int id_emp) throws SQLException {
        Connection conn=DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs = conn.prepareCall("{call sp_generar_entrega(?, ?, ?, ?, ?, ?, ?)}");
        cs.setInt(1, id_det);
        cs.setDate(2, Date.valueOf(LocalDate.now()));
        cs.setLong(3, kil);
        cs.setFloat(4, comb);
        cs.setString(5, descr);
        cs.setString(6, tipo);
        cs.setInt(7,id_emp);
        cs.execute();
        conn.close();
    }

}
