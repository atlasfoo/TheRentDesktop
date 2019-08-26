package therent.control.auto;

import therent.model.auto.CategoriaModel;
import therent.model.beans.Categoria;

import java.util.List;

public class CCategoria {
    public static List<Categoria> getAll() throws Exception{
        return new CategoriaModel().getAllCategoria();
    }

}
