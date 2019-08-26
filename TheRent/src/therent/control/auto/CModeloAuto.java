package therent.control.auto;

import therent.model.auto.ModeloAutoModel;
import therent.model.beans.ModeloAuto;

import java.util.List;

public class CModeloAuto {
    public static List<ModeloAuto> getAllModelos() throws Exception{
        return new ModeloAutoModel().getAllModelos();
    }
    public static void newModelo(String marca, String modelo, String motor, String carr, int combustible, int id_cat) throws Exception {
        if(marca.isEmpty() || modelo.isEmpty() || motor.isEmpty() || carr.isEmpty() || marca==null || modelo==null || motor==null || carr==null || id_cat<0 || combustible<0){
            throw new Exception("Por favor rellene todos los campos!");
        }
        new ModeloAutoModel().addModelo(marca, modelo, motor, carr, combustible, id_cat);
    }
}
