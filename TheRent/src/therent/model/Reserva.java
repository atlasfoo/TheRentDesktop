package therent.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import therent.util.JDBCUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;

public class Reserva {
    private IntegerProperty id_cliente;
    private StringProperty estados;
    private IntegerProperty id_car;
    private ObjectProperty<LocalDate> fecha_entrega;
    private ObjectProperty<LocalDate> fecha_recibo;
    private DoubleProperty costo;

    //constructor vacío
    public Reserva() {
    }

    //constructor completo
    public Reserva(int IDCliente, String estados, int IDAuto , Date fecha_entrega,Date fecha_recibo, Double costo) {
        this.id_cliente= new SimpleIntegerProperty(IDCliente);
        this.estados = new SimpleStringProperty(estados);
        this.id_car = new SimpleIntegerProperty(IDAuto);
        this.fecha_entrega = new SimpleObjectProperty<LocalDate>();
        this.fecha_recibo = new SimpleObjectProperty<LocalDate>();
        this.costo = new SimpleDoubleProperty(costo);
    }
    //getters y setters

    public int getIdCliente() {
        return id_cliente.get();
    }

    public IntegerProperty IdClienteProperty() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente.set(id_cliente);
    }


    public String getEstados() {
        return estados.get();
    }

    public StringProperty EstadosProperty() {
        return estados;
    }

    public void setEstados(String estados) {
        this.estados.set(estados);
    }


    public int getIDAuto() {
        return id_car.get();
    }

    public IntegerProperty IDAutoProperty() {
        return id_car;
    }

    public void setIDAuto(int IDAuto) {
        this.id_car.set(IDAuto);
    }

    public Object getFecha_entrega() {
        return fecha_entrega.get();
    }

    public void setFecha_entrega(Object fecha_entrega) {
        this.fecha_entrega=new SimpleObjectProperty(fecha_entrega);
    }

    public Object getFecha_recibo() {
        return fecha_recibo.get();
    }

    public void setFecha_recibo(Object fecha_recibo) {
        this.fecha_recibo=new SimpleObjectProperty(fecha_recibo);
    }

    public Double getCosto() {
        return costo.get();
    }

    public DoubleProperty costo() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo.set(costo);
    }

    //Metodo para ingresar cliente a la base de datos
    public void IngresarReserva(Reserva reserva) throws  Exception
    {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_agregar_reserva(?, ?, ?, ?, ?, ?)}");
        cs.setInt(1,reserva.getIdCliente());
        cs.setString(2,reserva.getEstados());
        cs.setInt(3,reserva.getIDAuto());
        cs.setDate(4,(java.sql.Date) reserva.getFecha_entrega());
        cs.setDate(5,(java.sql.Date) reserva.getFecha_recibo());
        cs.setDouble(6,reserva.getCosto());
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
    public ObservableList<Reserva> MostrarRegistros()
    {
        //Declaracion de variables
        ObservableList<Reserva> ListaReserva;
        //Inicializando el observablelist
        ListaReserva = FXCollections.observableArrayList();

        try {
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            CallableStatement cs = conn.prepareCall("{call sp_mostrar_reserva}");
            ResultSet resultado = cs.executeQuery();

            while (resultado.next())
            {
                ListaReserva.add(new Reserva
                        (
                                resultado.getInt("Id_Cliente"),
                                resultado.getString("Estado"),
                                resultado.getInt("Id_Auto"),
                                resultado.getDate("Fecha_Entrega"),
                                resultado.getDate("Fecha_Recibo"),
                                resultado.getDouble("Costo")
                        ));
            }

        }catch (Exception e){}

        return  ListaReserva;
    }

    //Metodo para BuscarRegistro

    public ObservableList<Reserva> BuscarRegistro(String a)
    {
        //Declaracion de variables
        ObservableList<Reserva> ListaReserva;
        //Inicializando el observablelist
        ListaReserva = FXCollections.observableArrayList();

        try {
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            CallableStatement cs = conn.prepareCall("{CALL sp_buscar_reserva (?)}");
            cs.setString(1,a);
            ResultSet resultado = cs.executeQuery();

            while (resultado.next())
            {
                ListaReserva.add(new Reserva
                        (
                                resultado.getInt("Id_Cliente"),
                                resultado.getString("Estado"),
                                resultado.getInt("Id_Auto"),
                                resultado.getDate("Fecha_Entrega"),
                                resultado.getDate("Fecha_Recibo"),
                                resultado.getDouble("Costo")

                        ));
            }

        }catch (Exception e){}

        return  ListaReserva;
    }


}
