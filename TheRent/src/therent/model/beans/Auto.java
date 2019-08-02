package therent.model.beans;

import javafx.beans.property.*;

public class Auto {
    private IntegerProperty IDAuto;
    private StringProperty marca;
    private StringProperty modelo;
    private StringProperty color;
    private IntegerProperty anho;
    private StringProperty transmision;
    private StringProperty carrocerria;
    private StringProperty combustible;
    private StringProperty placa;
    private StringProperty VIN;
    private StringProperty categoria;
    private FloatProperty precio;
    private FloatProperty deposito;
    private StringProperty estado;
    private StringProperty isenabled;

    //constructor vacío
    public Auto() {
    }

    //constructor completo
    public Auto(int IDAuto, String marca, String modelo, String color, int anho, String transmision, String carrocerria, String combustible, String placa, String VIN, String categoria, float precio, float deposito, String estado, String y_n) {
        this.IDAuto = new SimpleIntegerProperty(IDAuto);
        this.marca = new SimpleStringProperty( marca);
        this.modelo = new SimpleStringProperty( modelo);
        this.color = new SimpleStringProperty( color);
        this.anho = new SimpleIntegerProperty( anho);
        this.transmision = new SimpleStringProperty( transmision);
        this.carrocerria = new SimpleStringProperty( carrocerria);
        this.combustible = new SimpleStringProperty( combustible);
        this.placa = new SimpleStringProperty( placa);
        this.VIN = new SimpleStringProperty( VIN);
        this.categoria = new SimpleStringProperty( categoria);
        this.precio = new SimpleFloatProperty( precio);
        this.deposito = new SimpleFloatProperty( deposito);
        this.estado = new SimpleStringProperty( estado);
        this.isenabled=new SimpleStringProperty(y_n);
    }
    //getters y setters

    public int getIDAuto() {
        return IDAuto.get();
    }

    public IntegerProperty IDAutoProperty() {
        return IDAuto;
    }

    public void setIDAuto(int IDAuto) {
        this.IDAuto.set(IDAuto);
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

    public String getColor() {
        return color.get();
    }

    public StringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public int getAnho() {
        return anho.get();
    }

    public IntegerProperty anhoProperty() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho.set(anho);
    }

    public String getTransmision() {
        return transmision.get();
    }

    public StringProperty transmisionProperty() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision.set(transmision);
    }

    public String getCarrocerria() {
        return carrocerria.get();
    }

    public StringProperty carrocerriaProperty() {
        return carrocerria;
    }

    public void setCarrocerria(String carrocerria) {
        this.carrocerria.set(carrocerria);
    }

    public String getCombustible() {
        return combustible.get();
    }

    public StringProperty combustibleProperty() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible.set(combustible);
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

    public String getVIN() {
        return VIN.get();
    }

    public StringProperty VINProperty() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN.set(VIN);
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

    public float getDeposito() {
        return deposito.get();
    }

    public FloatProperty depositoProperty() {
        return deposito;
    }

    public void setDeposito(float deposito) {
        this.deposito.set(deposito);
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

    public String getIsenabled() {
        return isenabled.get();
    }

    public StringProperty isenabledProperty() {
        return isenabled;
    }

    public void setIsenabled(String isenabled) {
        this.isenabled.set(isenabled);
    }
}

