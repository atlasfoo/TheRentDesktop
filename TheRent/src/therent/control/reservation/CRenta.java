package therent.control.reservation;

import therent.model.beans.Renta;
import therent.model.reservation.RentaModel;

import java.util.List;

public class CRenta {
    public static List<Renta> getRentas() throws Exception{
        return new RentaModel().AllRenta();
    }
}
