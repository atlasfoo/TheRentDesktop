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
    private IntegerProperty id_Auto;
    private StringProperty marca;
    private StringProperty modelo;



    //constructor vacío
    public TablaVistaA()
    {

    }

    //constructor completo
    public TablaVistaA(int Id_Auto, String Marca, String Modelo) {
        this.id_Auto = new SimpleIntegerProperty(Id_Auto);
        this.marca = new SimpleStringProperty(Marca);
        this.modelo = new SimpleStringProperty(Modelo);
    }
    //getters y setters

    public int getId_Auto() {
        return id_Auto.get();
    }

    public IntegerProperty Id_AutoProperty() {
        return id_Auto;
    }

    public void setId_Auto(int Id_Auto) {
        this.id_Auto.set(Id_Auto);
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
        conn.close();
        }catch (Exception e){}

        return  ListaReserva;
    }



}
