package therent.util.converters;

import javafx.util.StringConverter;
import therent.model.beans.ModeloAuto;

public class ModeloAutoConverter extends StringConverter<ModeloAuto> {

    @Override
    public String toString(ModeloAuto obj) {
        return obj == null ? null : (obj.getMarca()+" "+obj.getModelo()+" | "+obj.getCategoria()+" | "+obj.getPrecio()+" $");
    }

    @Override
    public ModeloAuto fromString(String string) {
        return null;
    }
}
