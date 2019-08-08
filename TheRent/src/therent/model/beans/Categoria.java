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

    public int getId_categoria() {
        return id_categoria.get();
    }

    public IntegerProperty id_categoriaProperty() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria.set(id_categoria);
    }

    public String getCategoria() {
        return categoria.get();
    }

    public StringProperty categoriaProperty() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }

    public String getDescr() {
        return descr.get();
    }

    public StringProperty descrProperty() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr.set(descr);
    }

    public float getPrecio() {
        return precio.get();
    }

    public FloatProperty precioProperty() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio.set(precio);
    }

    public float getDeposito() {
        return deposito.get();
    }

    public FloatProperty depositoProperty() {
        return deposito;
    }

    public void setDeposito(float deposito) {
        this.deposito.set(deposito);
    }
}
