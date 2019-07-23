package therent.control;

import therent.model.LoginModel;

import java.sql.SQLException;

public class CLogin {
    public static String Login(String usr, String pswd) throws Exception {
        if(usr.isEmpty() || usr==null || pswd.isEmpty() || pswd==null){
            throw new Exception("Rellene correctamente los campos");
        }
        return new LoginModel().login(usr, pswd);
    }
}
