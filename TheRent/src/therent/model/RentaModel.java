package therent.model;

import javafx.beans.property.*;
import javafx.scene.control.Alert;
import therent.util.JDBCUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

public class RentaModel {
    private IntegerProperty id_cliente;
    private StringProperty estado;
    private ObjectProperty<Date> fecha;
    private IntegerProperty Id_Renta;

    public RentaModel(){

    }

    public RentaModel(int Id_Cliente, String Estado){
        this.id_cliente = new SimpleIntegerProperty(Id_Cliente);
        this.estado = new SimpleStringProperty(Estado);
    }

    public int getId_Cliente() {
        return id_cliente.get();
    }

    public IntegerProperty Id_Cliente_Property() {
        return id_cliente;
    }

    public void setId_Cliente(int id_cliente) {
        this.id_cliente.set(id_cliente);
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty Estado_Property() {
        return estado;
    }

    public void setEstado(String Estado) {
        this.estado.set(Estado);
    }

    //Metodo para ingresar cliente a la base de datos
    public void IngresarRenta(RentaModel modelorenta) throws  Exception
    {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_new_renta_view(?, ?)}");
        cs.setInt(1,modelorenta.getId_Cliente());
        cs.setString(2,modelorenta.getEstado());
        cs.execute();
        msgerr("Correcto");
        conn.close();
    }

    //Metodo para mandar mensajes
    public void msgerr(String msg){
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }

}
