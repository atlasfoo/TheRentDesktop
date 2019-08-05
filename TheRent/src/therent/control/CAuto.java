package therent.control;

import therent.model.AutoModel;
import therent.model.beans.Auto;

import java.util.List;

public class CAuto {
    public static List<Auto> getAutos() throws Exception{
        return new AutoModel().getAllAutos();
    }

    public static boolean addAuto(String plac, int yr, String chasis, String vin, String color, int trans, int id_mod) throws Exception{
        if(plac=="" || chasis=="" || vin=="" || color=="" || plac==null || chasis==null || vin==null || color==null || yr<0 || trans<0 || id_mod<0){
            throw new Exception("Por favor rellene todos los campos correctamente!!!");
        }
        return new AutoModel().newAuto(plac, yr, chasis,vin ,color,trans,id_mod);
    }

}
