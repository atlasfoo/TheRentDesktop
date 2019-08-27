package therent.control.reservation;

import therent.model.beans.DetalleRenta;
import therent.model.reservation.DetalleRentaModel;

import java.sql.SQLException;
import java.util.List;

public class CDetalleRenta {
    public static List<DetalleRenta> allDetail(int id) throws SQLException {
        return new DetalleRentaModel().Mostrarlasreservas(id);
    }
}
