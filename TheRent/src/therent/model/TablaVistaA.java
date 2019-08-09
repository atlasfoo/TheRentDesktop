package therent.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import therent.util.JDBCUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class TablaVistaA {

    //auto
    private IntegerProperty id_car;
    private StringProperty marca;
    private StringProperty modelo;



    //constructor vacío
    public TablaVistaA()
    {

    }

    //constructor completo
    public TablaVistaA(int IDCar, String Marca, String Modelo) {
        this.id_car = new SimpleIntegerProperty(IDCar);
        this.marca = new SimpleStringProperty(Marca);
        this.modelo = new SimpleStringProperty(Modelo);
    }
    //getters y setters

    public int getIDAuto() {
        return id_car.get();
    }

    public IntegerProperty IDAutoProperty() {
        return id_car;
    }

    public void setIDAuto(int IDAuto) {
        this.id_car.set(IDAuto);
    }


    public String getMarca() {
        return marca.get();
    }

    public StringProperty MarcaProperty() {
        return marca;
    }

    public void setMarca(String Marca) {
        this.marca.set(Marca);
    }

    public String getModelo() {
        return modelo.get();
    }

    public StringProperty ModeloProperty() {
        return modelo;
    }

    public void setModelo(String Modelo) {
        this.modelo.set(Modelo);
    }

    //Metodo para mostrar todos los Autos en mi reserva
    public ObservableList<TablaVistaA> MostrarAutos()
    {
        //Declaracion de variables
        ObservableList<TablaVistaA> ListaReserva;
        //Inicializando el observablelist
        ListaReserva = FXCollections.observableArrayList();

        try {
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            CallableStatement cs = conn.prepareCall("{call sp_auto_vista}");
            ResultSet resultado = cs.executeQuery();

            while (resultado.next())
            {
                ListaReserva.add(new TablaVistaA
                        (
                                resultado.getInt("Id_Auto"),
                                resultado.getString("Marca"),
                                resultado.getString("Modelo")
                        ));
            }

        }catch (Exception e){}

        return  ListaReserva;
    }



}
