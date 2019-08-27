package therent.control.reservation;

import therent.model.beans.Renta;
import therent.model.reservation.RentaModel;

import java.util.List;

public class CRenta {
    public static List<Renta> getRentas() throws Exception{
        return new RentaModel().AllRenta();
    }
    public static void addRenta(int id_cliente, int id_empleado) throws Exception {
        new RentaModel().IngresarRenta(id_cliente, id_empleado);
    }

}
