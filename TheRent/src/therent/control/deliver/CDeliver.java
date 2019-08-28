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

    public static void addEntrega(int id_det, long kil, float comb, String descr, String tipo, int id_emp) throws Exception {
        if(id_det<0 || id_emp<0 || kil<0 || comb <0 || comb>1 || descr.isEmpty() || descr==null || tipo.isEmpty() || tipo==null){
            throw new Exception("Rellene los campos correctamente");
        }
        new EntregaModel().addEntrega(id_det,kil,comb,descr,tipo,id_emp);
    }
}
