package therent.model;

import javafx.util.StringConverter;
import therent.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDate;


public class MantenimientoModel {
    public void addMantenimiento(LocalDate f_in, LocalDate f_out, String descr, int id_auto) throws SQLException {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_add_mantenimiento(?,?,?,?)}");
        cs.setDate(1, Date.valueOf(f_in));
        cs.setDate(2, Date.valueOf(f_out));
        cs.setString(3, descr);
        cs.setInt(4, id_auto);
    }

}
