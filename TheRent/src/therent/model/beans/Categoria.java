package therent.model.beans;

import javafx.beans.property.*;

public class Categoria {
    private IntegerProperty id_categoria;
    private StringProperty categoria;
    private StringProperty descr;
    private FloatProperty precio;
    private FloatProperty deposito;

    public Categoria() {
    }

    public Categoria(int id_categoria, String categoria, String descr, float precio, float deposito) {
        this.id_categoria =new SimpleIntegerProperty( id_categoria);
        this.categoria =new SimpleStringProperty( categoria);
        this.descr =new SimpleStringProperty( descr);
        this.precio =new SimpleFloatProperty( precio);
        this.deposito =new SimpleFloatProperty( deposito);
    }
}
