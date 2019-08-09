package therent.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import therent.util.JDBCUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class TablaVistaC
{
    //cliente
    private IntegerProperty Id_Cliente;
    private StringProperty Primer_Nombre;
    private StringProperty Primer_Apellido;

    //constructor vacío
    public TablaVistaC()
    {

    }

    //constructor completo
    public TablaVistaC(int IDCliente, String PrimerNombre, String PrimerApellido) {
        this.Id_Cliente = new SimpleIntegerProperty(IDCliente);
        this.Primer_Nombre = new SimpleStringProperty(PrimerNombre);
        this.Primer_Apellido = new SimpleStringProperty(PrimerApellido);
    }
    //getters y setters

    public int getIdCliente() {
        return Id_Cliente.get();
    }

    public IntegerProperty IdClienteProperty() {
        return Id_Cliente;
    }

    public void setIdcliente(int id_cliente) {
        this.Id_Cliente.set(id_cliente);
    }

    public String getPrimerNombre() {
        return Primer_Nombre.get();
    }

    public StringProperty PrimerNombreProperty() {
        return Primer_Nombre;
    }

    public void setPrimer_Nombre(String primer_nombre) {
        this.Primer_Nombre.set(primer_nombre);
    }

    public String getPrimerApellido() {
        return Primer_Apellido.get();
    }

    public StringProperty PrimerApellidoProperty() {
        return Primer_Apellido;
    }

    public void setPrimer_Apellido(String primer_apellido) {
        this.Primer_Apellido.set(primer_apellido);
    }

    //Metodo para mostrar todos los clientes en mi reserva
    public ObservableList<TablaVistaC> MostrarClientes()
    {
        //Declaracion de variables
        ObservableList<TablaVistaC> ListaReserva;
        //Inicializando el observablelist
        ListaReserva = FXCollections.observableArrayList();

        try {
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            CallableStatement cs = conn.prepareCall("{call sp_cliente_vista}");
            ResultSet resultado = cs.executeQuery();

            while (resultado.next())
            {
                ListaReserva.add(new TablaVistaC
                        (
                                resultado.getInt("Id_Cliente"),
                                resultado.getString("Primer_Nombre"),
                                resultado.getString("Primer_Apellido")
                        ));
            }

        }catch (Exception e){}

        return  ListaReserva;
    }

}
