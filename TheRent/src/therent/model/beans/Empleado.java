package therent.model.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Empleado {
    private IntegerProperty id;
    private StringProperty primer_nombre;
    private StringProperty segundo_nombre;
    private StringProperty primer_apellido;
    private StringProperty segundo_apellido;
    private StringProperty user_role;

    public Empleado() {
    }

    public Empleado(int id, String primer_nombre, String segundo_nombre, String primer_apellido, String segundo_apellido, String user_role) {
        this.id = new SimpleIntegerProperty(id);
        this.primer_nombre = new SimpleStringProperty(primer_nombre);
        this.segundo_nombre = new SimpleStringProperty(segundo_nombre);
        this.primer_apellido = new SimpleStringProperty(primer_apellido);
        this.segundo_apellido = new SimpleStringProperty(segundo_apellido);
        this.user_role = new SimpleStringProperty(user_role);
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

    public String getPrimer_nombre() {
        return primer_nombre.get();
    }

    public StringProperty primer_nombreProperty() {
        return primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre.set(primer_nombre);
    }

    public String getSegundo_nombre() {
        return segundo_nombre.get();
    }

    public StringProperty segundo_nombreProperty() {
        return segundo_nombre;
    }

    public void setSegundo_nombre(String segundo_nombre) {
        this.segundo_nombre.set(segundo_nombre);
    }

    public String getPrimer_apellido() {
        return primer_apellido.get();
    }

    public StringProperty primer_apellidoProperty() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido.set(primer_apellido);
    }

    public String getSegundo_apellido() {
        return segundo_apellido.get();
    }

    public StringProperty segundo_apellidoProperty() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido.set(segundo_apellido);
    }

    public String getUser_role() {
        return user_role.get();
    }

    public StringProperty user_roleProperty() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role.set(user_role);
    }
}
