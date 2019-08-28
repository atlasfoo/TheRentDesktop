package therent.control.reservation;

import therent.model.beans.DetalleRenta;
import therent.model.reservation.DetalleRentaModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CDetalleRenta {
    public static List<DetalleRenta> allDetail(int id) throws SQLException {
        return new DetalleRentaModel().Mostrarlasreservas(id);
    }
    public static void insertDetail(int id_renta, int id_auto, LocalDate f_in, LocalDate f_out) throws Exception {
        if(id_renta<1 || id_auto < 1 || f_in==null || f_out==null){
            throw new Exception("Rellene todos los campos correctamente");
        }
        if(f_out.isBefore(f_in) || f_out.isBefore(LocalDate.now()) || f_in.isBefore(LocalDate.now())){
            throw new Exception("Ingrese las fechas correctamente");
        }
        new DetalleRentaModel().IngresarDetalleRenta(id_renta, id_auto, f_in, f_out);
    }
    public static boolean editDetail(int id_det, int id_aut, LocalDate f_in, LocalDate f_out) throws Exception {
        if(id_det<1 || id_aut < 1 || f_in==null || f_out==null){
            throw new Exception("Rellene todos los campos correctamente");
        }
        if(f_out.isBefore(f_in) || f_out.isBefore(LocalDate.now()) || f_in.isBefore(LocalDate.now())){
            throw new Exception("Ingrese las fechas correctamente");
        }
        return new DetalleRentaModel().editDetail(id_det, id_aut, f_in, f_out);
    }
}
