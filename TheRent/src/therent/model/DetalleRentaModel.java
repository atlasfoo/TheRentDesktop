package therent.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import therent.control.CRentaDialog;
import therent.util.JDBCUtil;

import java.sql.*;

public class DetalleRentaModel {
    private IntegerProperty Id_detalle_renta;
    private IntegerProperty Id_Auto;
    private IntegerProperty Id_Empleado;
    private ObjectProperty<Date> Fecha_Entrega;
    private ObjectProperty<Date> Fecha_Recibo;
    private DoubleProperty costo;

    public DetalleRentaModel() {

    }

    public DetalleRentaModel(int idauto, Date fecha_entrega, Date fecha_recibo, Double costo) {
        this.Id_Auto = new SimpleIntegerProperty(idauto);
        this.Fecha_Entrega = new SimpleObjectProperty<>(fecha_entrega);
        this.Fecha_Recibo = new SimpleObjectProperty<>(fecha_recibo);
        this.costo = new SimpleDoubleProperty(costo);
    }

    //get y set cliente
    public int getId_Cliente() {
        return Id_Empleado.get();
    }

    public IntegerProperty Id_Cliente_Property() {
        return Id_Empleado;
    }

    public void setId_Cliente(int id_empleado) {
        this.Id_Empleado.set(id_empleado);
    }

    //get y set auto
    public int getId_Auto() {
        return Id_Auto.get();
    }

    public IntegerProperty Id_detalle_renta(){
        return Id_Auto;
    }

    public void setId_Auto(int id_Auto) {
        this.Id_Auto.set(id_Auto);
    }

    //get y set fecha entrega

    public Date getfecha_entrega(){
        return Fecha_Entrega.getValue();
    }

    public ObjectProperty<Date> fecha_EntregaProperty() {
        return Fecha_Entrega;
    }

    public void setfecha_entrega(Date fechaentrega){
        this.Fecha_Entrega.setValue(fechaentrega);
    }

    //get y set fecha_recibo

    public Date getfecha_recibo(){
        return Fecha_Recibo.getValue();
    }

    public ObjectProperty<Date> fecha_ReciboProperty() {
        return Fecha_Recibo;
    }

    public void setfecha_Recibo(Date fecharecibo){
        this.Fecha_Recibo.setValue(fecharecibo);
    }

    //get y set costo


    public double getCosto() {
        return costo.get();
    }

    public DoubleProperty costoProperty() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo.set(costo);
    }

    //Metodo para ingresar cliente a la base de datos
    public void IngresarDetalleRenta(DetalleRentaModel modeloDetallerenta) throws  Exception
    {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_new_detalle_renta(?, ?, ?, ?)}");

        cs.setInt(1,modeloDetallerenta.getId_Auto());
        cs.setDate(2,modeloDetallerenta.getfecha_entrega());
        cs.setDate(3,modeloDetallerenta.getfecha_recibo());
        cs.setDouble(4,modeloDetallerenta.getCosto());
        cs.execute();
        msgerr("Correcto");
        conn.close();
    }

    //Metodo para EliminarRegistro
    public ObservableList<DetalleRentaModel> eliminar_registro(Integer a)
    {
        //Declaracion de variables
        ObservableList<DetalleRentaModel> listaCliente;
        //Inicializando el observablelist
        listaCliente = FXCollections.observableArrayList();

        try {
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            CallableStatement cs = conn.prepareCall("{CALL sp_delete_detalle_renta(?)}");
            cs.setInt(1,a);
            ResultSet resultado = cs.executeQuery();

            while (resultado.next())
            {
                /*aqui se llena la coleccion con objetos de tipo cliente*/
                /*el resultado.getSting(parametro) se captura el valor que general el proceso almacenado*/
                listaCliente.add(new DetalleRentaModel
                        (
                                resultado.getInt("Id_Auto"),
                                resultado.getDate("Fecha_Entrega"),
                                resultado.getDate("Fecha_Recibo"),
                                resultado.getDouble("Costo")
                        ));
            }
            conn.close();
        }catch (Exception e){}

        return  listaCliente;
    }


    //Metodo para la dsiponibilidad del auto
    public ObservableList<Auto> disponibilidad_auto(Date fecha_entrega, Date fecha_recibo)
    {
        //Declaracion de variables
        ObservableList<Auto> listaAuto;
        //Inicializando el observablelist
        listaAuto = FXCollections.observableArrayList();

        try {
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            CallableStatement cs = conn.prepareCall("{call sp_disponibilidad_auto(?,?)}"
            );
            cs.setDate(1,fecha_entrega);
            cs.setDate(2,fecha_recibo);
            ResultSet resultado = cs.executeQuery();

            while (resultado.next())
            {
                listaAuto.add(new Auto
                        (
                                resultado.getInt("Id_Auto"),
                                resultado.getString("Marca"),
                                resultado.getString("Modelo"),
                                resultado.getString("Color"),
                                resultado.getInt("Año"),
                                resultado.getString("Transmisión"),
                                resultado.getString("Carrocería"),
                                resultado.getString("Combustible"),
                                resultado.getString("Placa"),
                                resultado.getString("VIN"),
                                resultado.getString("Chasis"),
                                resultado.getString("Categoría"),
                                resultado.getFloat("Precio"),
                                resultado.getFloat("Depósito"),
                                resultado.getString("Habilitado"),
                                resultado.getString("Estado")
                        ));
            }
            conn.close();
        }catch (Exception e){}

        return  listaAuto;
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
