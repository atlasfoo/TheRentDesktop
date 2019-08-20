package therent.model.beans;

import javafx.beans.property.*;

public class ModeloAuto {
    private IntegerProperty id_modelo;
    private StringProperty marca;
    private StringProperty modelo;
    private StringProperty categoria;
    private FloatProperty precio;

    public ModeloAuto() {
    }

    public ModeloAuto(int id_modelo, String marca, String modelo, String categoria, float precio) {
        this.id_modelo = new SimpleIntegerProperty( id_modelo);
        this.marca = new SimpleStringProperty( marca);
        this.modelo = new SimpleStringProperty( modelo);
        this.categoria = new SimpleStringProperty( categoria);
        this.precio = new SimpleFloatProperty( precio);
    }

    public int getId_modelo() {
        return id_modelo.get();
    }

    public IntegerProperty id_modeloProperty() {
        return id_modelo;
    }

    public void setId_modelo(int id_modelo) {
        this.id_modelo.set(id_modelo);
    }

    public String getMarca() {
        return marca.get();
    }

    public StringProperty marcaProperty() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public String getModelo() {
        return modelo.get();
    }

    public StringProperty modeloProperty() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
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

    public float getPrecio() {
        return precio.get();
    }

    public FloatProperty precioProperty() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio.set(precio);
    }
}
