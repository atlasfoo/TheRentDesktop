package therent.model;

import therent.util.JDBCUtil;

import java.sql.*;

public class LoginModel {
    public LoginModel(){
    }

    public String login(String usr, String pswd) throws SQLException{
        //se crea la conexión
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        /*se carga el procedimiento almacenado
        * 1: usuario
        * 2: contraseña
        * 3: parametro que retorna el rol, tipo varchar*/
        CallableStatement cs=conn.prepareCall("{call sp_login_sysuser(?, ?, ?)}");
        cs.setString(1, usr);
        cs.setString(2, pswd);
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.execute();
        String role=cs.getString(3);
        conn.close();
        return role;
    }
}
