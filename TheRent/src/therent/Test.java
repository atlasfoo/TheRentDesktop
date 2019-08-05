package therent;

import therent.model.ModeloAutoModel;
import therent.model.beans.ModeloAuto;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            List<ModeloAuto> ma= new ModeloAutoModel().getAllModelos();
            for (ModeloAuto mad:
                 ma) {
                System.out.printf("%s | %s | %s | %s\n", mad.getMarca(), mad.getModelo(), mad.getCategoria(), mad.getPrecio());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
