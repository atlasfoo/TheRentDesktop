package therent.model;

import therent.model.beans.Empleado;
import therent.util.JDBCUtil;

import java.sql.*;

public class LoginModel {
    public LoginModel(){
    }

    public Empleado login(String usr, String pswd) throws SQLException{
        //se crea la conexión
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        /*se carga el procedimiento almacenado
        * 1: usuario
        * 2: contraseña
        * 3: parametro que retorna el rol, tipo varchar*/
        CallableStatement cs=conn.prepareCall("{call sp_login_sysuser(?, ?)}");
        cs.setString(1, usr);
        cs.setString(2, pswd);
        ResultSet rs=cs.executeQuery();
        if(!rs.next()){
            return null;
        }else{
            rs.beforeFirst();
        }
        Empleado session=new Empleado();
        while(rs.next()){
            session=new Empleado(
                    rs.getInt("Id_Empleado"),
                    rs.getString("Primer_Nombre"),
                    rs.getString("Segundo_Nombre"),
                    rs.getString("Primer_Apellido"),
                    rs.getString("Segundo_Apellido"),
                    rs.getString("rol")
            );
        }
        conn.close();
        return session;
    }
}
