package therent.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import therent.util.JDBCUtil;

import java.sql.*;

public class Cliente {

    private StringProperty nombre1;
    private StringProperty nombre2;
    private StringProperty apellido1;
    private StringProperty apellido2;
    private StringProperty cedula ;
    private StringProperty direccion;
    private StringProperty tipocliente;
    private StringProperty estado;

    public Cliente(String nombre1, String nombre2, String apellido1, String apellido2, String cedula, String direccion, String tipocliente)
    {
        this.nombre1 = new SimpleStringProperty(nombre1);
        this.nombre2 = new SimpleStringProperty(nombre2);
        this.apellido1 = new SimpleStringProperty(apellido1);
        this.apellido2 = new SimpleStringProperty(apellido2);
        this.cedula = new SimpleStringProperty(cedula);
        this.direccion = new SimpleStringProperty(direccion);
        this.tipocliente = new SimpleStringProperty(tipocliente);
    }

    public Cliente(String nombre1, String nombre2, String apellido1, String apellido2, String cedula, String direccion, String tipocliente, String estado)
    {
        this.nombre1 = new SimpleStringProperty(nombre1);
        this.nombre2 = new SimpleStringProperty(nombre2);
        this.apellido1 = new SimpleStringProperty(apellido1);
        this.apellido2 = new SimpleStringProperty(apellido2);
        this.cedula = new SimpleStringProperty(cedula);
        this.direccion = new SimpleStringProperty(direccion);
        this.tipocliente = new SimpleStringProperty(tipocliente);
        this.estado = new SimpleStringProperty(estado);
    }

    public  Cliente(){}

    public String getNombre1()
    {
        return  nombre1.get();
    }

    public void setNombre1(String nombre1)
    {
        this.nombre1 = new SimpleStringProperty(nombre1);
    }

    public String getNombre2()
    {
        return  nombre2.get();
    }

    public void setNombre2(String nombre2)
    {
        this.nombre2 = new SimpleStringProperty(nombre2);
    }

    public String getApellido1()
    {
        return  apellido1.get();
    }

    public void setApellido1(String apellido1)
    {
        this.apellido1 = new SimpleStringProperty(apellido1);
    }

    public String getApellido2()
    {
        return  apellido2.get();
    }

    public void setApellido2(String apellido2)
    {
        this.apellido2 = new SimpleStringProperty(apellido2);
    }

    public String getCedula()
    {
        return cedula.get();
    }

    public void setCedula(String cedula)
    {
        this.cedula = new SimpleStringProperty(cedula);
    }

    public String getDireccion()
    {
        return  direccion.get();
    }

    public void setDireccion(String direccion)
    {
        this.direccion = new SimpleStringProperty(direccion);
    }

    public String gettipocliente()
    {
        return  tipocliente.get();
    }

    public void setTipocliente(String  tipocliente)
    {
        this.tipocliente = new SimpleStringProperty(tipocliente);
    }

    public String getEstado() { return estado.get(); }

    public void setEstado(String estado) { this.estado = new SimpleStringProperty(estado); }


    //Metodos get y set property

    public StringProperty nombre1property()
    {
        return nombre1;
    }

    public StringProperty nombre2property()
    {
        return nombre2;
    }

    public StringProperty apellido1Property(){

        return apellido1;

    }

    public StringProperty apellido2Property(){

        return apellido2;

    }

    public StringProperty cedulaProperty(){

        return cedula;

    }

    public StringProperty direccionProperty() {

        return direccion;

    }

    public StringProperty tipoClienteProperty() {

        return tipocliente;

    }

    public StringProperty estadoProperty() {

        return estado;

    }

//Metodo para ingresar cliente a la base de datos
    public void IngresarCliente(Cliente cliente) throws  Exception
    {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_new_cliente(?, ?, ?, ?, ?, ?, ?)}");
        cs.setString(1,cliente.getNombre1());
        cs.setString(2,cliente.getNombre2());
        cs.setString(3,cliente.getApellido1());
        cs.setString(4,cliente.getApellido2());
        cs.setString(5,cliente.getCedula());
        cs.setString(6,cliente.getDireccion());
        cs.setString(7,cliente.gettipocliente());
        cs.execute();

        msgerr("Correcto");
    }

    //Metodo para mandar mensajes
    public void msgerr(String msg){
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }

    //Metodo para mostrar todos los registros
    public ObservableList<Cliente> MostrarRegistros()
    {
        //Declaracion de variables
        ObservableList<Cliente> listaCliente;
        //Inicializando el observablelist
        listaCliente = FXCollections.observableArrayList();

        try {
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            CallableStatement cs = conn.prepareCall("{call sp_mostrar_cliente}");
            ResultSet resultado = cs.executeQuery();

            while (resultado.next())
            {
                listaCliente.add(new Cliente
                        (
                                resultado.getString("Primer_Nombre"),
                                resultado.getString("Segundo_Nombre"),
                                resultado.getString("Primer_Apellido"),
                                resultado.getString("Segundo_Apellido"),
                                resultado.getString("Cedula"),
                                resultado.getString("Dirreccion"),
                                resultado.getString("Tipo_Cliente"),
                                resultado.getString("Estado")
                              ));
            }

        }catch (Exception e){}

        return  listaCliente;
    }

}
