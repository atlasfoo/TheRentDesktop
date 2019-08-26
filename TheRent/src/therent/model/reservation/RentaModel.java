package therent.model.reservation;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import therent.util.JDBCUtil;

import java.sql.*;

public class RentaModel {

    private IntegerProperty id_cliente;
    private StringProperty Primer_Nombre;
    private StringProperty Segundo_Nombre;
    private StringProperty Primer_Apellido;
    private StringProperty Segundo_Apellido;
    private ObjectProperty<Date> fecha;
    private StringProperty estado;
    private IntegerProperty Id_Renta;

    public RentaModel(){

    }

    public RentaModel(String nombre1,String nombre2,String apellido1,String apellido2,Date fecha1,String estado1){
        this.Primer_Nombre = new SimpleStringProperty(nombre1);
        this.Primer_Apellido = new SimpleStringProperty(nombre2);
        this.Segundo_Nombre = new SimpleStringProperty(apellido1);
        this.Segundo_Apellido = new SimpleStringProperty(apellido2);
        this.fecha = new SimpleObjectProperty<>(fecha1);
        this.estado = new SimpleStringProperty(estado1);

    }

    public RentaModel(int Id_Cliente,String estado1){
        this.id_cliente = new SimpleIntegerProperty(Id_Cliente);
        this.estado = new SimpleStringProperty(estado1);

    }

    // get y set del id del cliente
    public int getId_Cliente() {
        return id_cliente.get();
    }

    public IntegerProperty Id_Cliente_Property() {
        return id_cliente;
    }

    public void setId_Cliente(int id_cliente) {
        this.id_cliente.set(id_cliente);
    }

    //get y set de primer nombre
    public String getPrimer_Nombre() {
        return Primer_Nombre.get();
    }

    public StringProperty Primer_Nombre_Porperty() {
        return Primer_Nombre;
    }

    public void setPrimer_Nombre(String primer_nombre) {
        this.Primer_Nombre.set(primer_nombre);
    }

    //get y set de segundo nombre
    public String getSegundo_Nombre() {
        return Segundo_Nombre.get();
    }

    public StringProperty Segundo_Nombre_Porperty() {
        return Segundo_Nombre;
    }

    public void setSegundo_Nombre(String Segundo_Nombre) {
        this.Segundo_Nombre.set(Segundo_Nombre);
    }


    //get y set de primer apellido
    public String getPrimer_Apellido() {
        return Primer_Apellido.get();
    }

    public StringProperty Primer_Apellido_Property() {
        return Primer_Apellido;
    }

    public void setPrimer_Apellido(String primer_apellido) {
        this.Primer_Apellido.set(primer_apellido);
    }

    //get y set de segundo apellido
    public String getSegundo_Apellido() {
        return Segundo_Apellido.get();
    }

    public StringProperty Segundo_Apellido_Porperty() {
        return Segundo_Apellido;
    }

    public void setSegundo_Apellido(String Segundo_Apellido) {
        this.Segundo_Apellido.set(Segundo_Apellido);
    }

    //get y set de fecha
    public Date getfecha(){
        return fecha.getValue();
    }

    public ObjectProperty<Date> fecha_Property() {
        return fecha;
    }

    public void setfecha(Date fecha){
        this.fecha.setValue(fecha);
    }

    //get y set de estado
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

    //Metodo para mostrar todos las rentas
    public ObservableList<RentaModel> MostrarRegistrosRenta() throws Exception
    {
        //Declaracion de variables
        ObservableList<RentaModel> listaRenta;
        //Inicializando el observablelist
        listaRenta = FXCollections.observableArrayList();


        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs = conn.prepareCall("{call sp_renta_cliente}");
        ResultSet resultado = cs.executeQuery();

        while (resultado.next())
        {
            listaRenta.add(new RentaModel
                    (
                            resultado.getString("Primer_Nombre"),
                            resultado.getString("Segundo_Nombre"),
                            resultado.getString("Primer_Apellido"),
                            resultado.getString("Segundo_Apellido"),
                            resultado.getDate("Fecha"),
                            resultado.getString("Estado")

                    ));
        }
        conn.close();

        return  listaRenta;
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
