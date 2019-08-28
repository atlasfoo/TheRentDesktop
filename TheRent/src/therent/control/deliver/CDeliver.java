package therent.control.deliver;

import therent.model.beans.DetalleEntrega;
import therent.model.deliver.EntregaModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CDeliver {
    public static List<DetalleEntrega> aEntregar() throws SQLException {
        return new EntregaModel().entregaToday(LocalDate.now());
    }

    public static List<DetalleEntrega> aRecibir() throws SQLException {
        return new EntregaModel().reciboToday(LocalDate.now());
    }
}
