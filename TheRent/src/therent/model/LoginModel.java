package therent.model;

import therent.conn.JDBCUtil;

import java.sql.*;

public class LoginModel {
    public LoginModel(){
    }

    public String login(String usr, String pswd) throws SQLException{
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_login_sysuser(?, ?, ?)}");
        cs.setString(1, usr);
        cs.setString(2, pswd);
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.execute();
        String role=cs.getString(3);
        System.out.println(role);
        return role;
    }
}
