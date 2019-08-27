package therent.control.car;

import therent.model.car.MantenimientoModel;

import java.time.LocalDate;

public class CMantenimiento {
    public static void addMant(LocalDate f_in, LocalDate f_out, String desc, int id_auto) throws Exception {
        if(f_in==null || f_out==null || desc.isEmpty() || desc==null || id_auto<1){
            throw new Exception("Por favor rellene todos los campos correctamente");
        }
        if(f_in.isAfter(f_out)){
            throw new Exception("Ingrese las fechas correctamente");
        }
        new MantenimientoModel().addMantenimiento(f_in,f_out,desc,id_auto);
    }
}
