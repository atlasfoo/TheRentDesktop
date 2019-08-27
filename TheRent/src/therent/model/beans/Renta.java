package therent.model.beans;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.logging.SocketHandler;

public class Renta {
    private IntegerProperty id;
    private StringProperty cliente;
    private StringProperty empleado;
    private ObjectProperty<LocalDate> fecha;
    private StringProperty estado;

    public Renta(){}

    public Renta(int id, String cliente, String empleado, LocalDate fecha, String estado){
        this.id=new SimpleIntegerProperty(id);
        this.cliente=new SimpleStringProperty(cliente);
        this.empleado=new SimpleStringProperty(empleado);
        this.fecha = new SimpleObjectProperty<>(fecha);
        this.estado = new SimpleStringProperty(estado);
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

    public String getCliente() {
        return cliente.get();
    }

    public StringProperty clienteProperty() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente.set(cliente);
    }

    public String getEmpleado() {
        return empleado.get();
    }

    public StringProperty empleadoProperty() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado.set(empleado);
    }

    public LocalDate getFecha() {
        return fecha.get();
    }

    public ObjectProperty<LocalDate> fechaProperty() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha.set(fecha);
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }
}
