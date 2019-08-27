package therent.model.beans;

import javafx.beans.property.*;

import java.time.LocalDate;

public class DetalleRenta {
    private IntegerProperty id;
    private StringProperty auto;
    private IntegerProperty año;
    private StringProperty color;
    private StringProperty placa;
    private ObjectProperty<LocalDate> f_in;
    private ObjectProperty<LocalDate> f_out;

    public DetalleRenta() {
    }

    public DetalleRenta(int id, String auto, int año, String color, String placa, LocalDate f_in, LocalDate f_out) {
        this.id =new SimpleIntegerProperty( id);
        this.auto =new SimpleStringProperty( auto);
        this.año =new SimpleIntegerProperty( año);
        this.color =new SimpleStringProperty( color);
        this.placa =new SimpleStringProperty( placa);
        this.f_in =new SimpleObjectProperty<>( f_in);
        this.f_out =new SimpleObjectProperty<>(f_out);
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

    public LocalDate getF_in() {
        return f_in.get();
    }

    public ObjectProperty<LocalDate> f_inProperty() {
        return f_in;
    }

    public void setF_in(LocalDate f_in) {
        this.f_in.set(f_in);
    }

    public LocalDate getF_out() {
        return f_out.get();
    }

    public ObjectProperty<LocalDate> f_outProperty() {
        return f_out;
    }

    public void setF_out(LocalDate f_out) {
        this.f_out.set(f_out);
    }
}
