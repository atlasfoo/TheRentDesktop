package therent.util.converters;

import javafx.util.StringConverter;
import therent.model.beans.Categoria;

public class CategoriaConverter extends StringConverter<Categoria> {

    @Override
    public String toString(Categoria o) {
        return o==null? null : ("Categoría "+o.getCategoria()+": "+o.getDescr()+" | "+o.getPrecio()+"$");
    }

    @Override
    public Categoria fromString(String string) {
        return null;
    }
}
