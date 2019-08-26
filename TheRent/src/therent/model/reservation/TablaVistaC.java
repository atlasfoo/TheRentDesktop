package therent.model.reservation;

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
    public TablaVistaC(int IdCliente, String PrimerNombre, String PrimerApellido) {
        this.Id_Cliente = new SimpleIntegerProperty(IdCliente);
        this.Primer_Nombre = new SimpleStringProperty(PrimerNombre);
        this.Primer_Apellido = new SimpleStringProperty(PrimerApellido);
    }
    //getters y setters

    public int getId_Cliente() {
        return Id_Cliente.get();
    }

    public IntegerProperty Id_Cliente_Property() {
        return Id_Cliente;
    }

    public void setId_Cliente(int id_cliente) {
        this.Id_Cliente.set(id_cliente);
    }

    public String getPrimer_Nombre() {
        return Primer_Nombre.get();
    }

    public StringProperty Primer_Nombre_Porperty() {
        return Primer_Nombre;
    }

    public void setPrimer_Nombre(String primer_nombre) {
        this.Primer_Nombre.set(primer_nombre);
    }

    public String getPrimer_Apellido() {
        return Primer_Apellido.get();
    }

    public StringProperty Primer_Apellido_Property() {
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
        conn.close();
        }catch (Exception e){}

        return  ListaReserva;
    }

}
