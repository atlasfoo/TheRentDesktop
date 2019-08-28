package therent.model.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class DetalleEntrega {
    private IntegerProperty id;
    private StringProperty auto;
    private IntegerProperty año;
    private StringProperty color;
    private StringProperty placa;
    private StringProperty cliente;

    public DetalleEntrega() {
    }

    public DetalleEntrega(int id, String auto, int año, String color, String placa, String cliente) {
        this.id = new SimpleIntegerProperty(id);
        this.auto = new SimpleStringProperty(auto);
        this.año = new SimpleIntegerProperty(año);
        this.color = new SimpleStringProperty(color);
        this.placa = new SimpleStringProperty(placa);
        this.cliente = new SimpleStringProperty(cliente);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getAuto() {
        return auto.get();
    }

    public StringProperty autoProperty() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto.set(auto);
    }

    public int getAño() {
        return año.get();
    }

    public IntegerProperty añoProperty() {
        return año;
    }

    public void setAño(int año) {
        this.año.set(año);
    }

    public String getColor() {
        return color.get();
    }

    public StringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public String getPlaca() {
        return placa.get();
    }

    public StringProperty placaProperty() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa.set(placa);
    }

    public String getCliente() {
        return cliente.get();
    }

    public StringProperty clienteProperty() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente.set(cliente);
    }
}
