package therent.control.car;

import therent.model.car.AutoModel;
import therent.model.beans.Auto;

import java.time.LocalDate;
import java.util.List;

public class CAuto {
    public static List<Auto> getAutos() throws Exception{
        return new AutoModel().getAllAutos();
    }

    public static boolean addAuto(String plac, int yr, String chasis, String vin, String color, int trans, int id_mod) throws Exception{
        if(plac=="" || chasis=="" || vin=="" || color=="" || plac==null || chasis==null || vin==null || color==null || yr<0 || trans<0 || id_mod<0){
            throw new Exception("Por favor rellene todos los campos correctamente");
        }
        return new AutoModel().newAuto(plac, yr, chasis,vin ,color,trans,id_mod);
    }

    public static void editAuto(int id, String plac, String col) throws Exception {
        if(id<1 || plac=="" || plac==null || col=="" || col==null){
            throw new Exception("Por favor rellene todos los campos editables correctamente");
        }
        new AutoModel().editAuto(id, plac, col);
    }

    public static void enable_disable_auto(int id) throws Exception {
        if(id<1){
            throw new Exception("Por favor seleccione un auto");
        }
        new AutoModel().enable_disable(id);
    }

    public static List<Auto> getDisponibility(LocalDate f_in, LocalDate f_out) throws Exception {
        if(f_in == null || f_out == null){
            throw new Exception("Por favor rellene todos los campos");
        }

        if(f_out.isBefore(f_in) || f_out.isBefore(LocalDate.now()) || f_in.isBefore(LocalDate.now())){
            throw new Exception("Fechas ingresadas incorrectamente");
        }

        return new AutoModel().disponibilidad_auto(f_in,f_out);
    }

    public static Auto getbyPlaca(String placa) throws Exception {
        if(placa.isEmpty() || placa==null){
            throw new Exception("Internal error type in method getbyPlaca");
        }
        return new AutoModel().getbyPlaca(placa);
    }
}
