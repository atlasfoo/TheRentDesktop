package therent.control;

import therent.model.ModeloAutoModel;
import therent.model.beans.ModeloAuto;

import java.util.List;

public class CModeloAuto {
    public static List<ModeloAuto> getAllModelos() throws Exception{
        return new ModeloAutoModel().getAllModelos();
    }
}
