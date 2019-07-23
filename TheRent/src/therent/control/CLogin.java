package therent.control;

import therent.model.LoginModel;

import java.sql.SQLException;
//EN LAS CLASES DE CONTROLADOR ES QUE SE DEBEN VALIDAR CORRECTAMENTE LOS CAMPOS
public class CLogin {
    //la excepcion genérica tambien recibira las SQLException del modelo
    public static String Login(String usr, String pswd) throws Exception {
        //se valida si se envió vacío o no se envió nada a traves de una excepcion
        if(usr.isEmpty() || usr==null || pswd.isEmpty() || pswd==null){
            throw new Exception("Rellene correctamente los campos");
        }
        return new LoginModel().login(usr, pswd);
    }
}
