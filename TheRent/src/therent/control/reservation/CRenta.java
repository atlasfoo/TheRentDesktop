package therent.control.reservation;

import therent.model.beans.Renta;
import therent.model.reservation.RentaModel;

import java.sql.SQLException;
import java.util.List;

public class CRenta {
    public static List<Renta> getRentas() throws Exception{
        return new RentaModel().AllRenta();
    }
    public static void addRenta(int id_cliente, int id_empleado) throws Exception {
        new RentaModel().IngresarRenta(id_cliente, id_empleado);
    }
    public static void changeStat(int id_rent, int stat) throws SQLException {
        new RentaModel().changeStat(id_rent, stat);
    }
}
