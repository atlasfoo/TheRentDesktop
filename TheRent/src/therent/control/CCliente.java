package therent.control;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import therent.model.Cliente;
import java.util.concurrent.ExecutionException;

public class CCliente {

    //Metodo estatico para ingresar cliente.
    public static void IngresarCliente(String a, String b, String c, String d, String e, String f, String g) throws Exception
    {
            Cliente cliente = new Cliente(a, b, c, d, e, f, g);
            cliente.IngresarCliente(cliente);

    }
    //Metodo estatico para mostrar los datos. Y retorna una coleccion
    public static ObservableList<Cliente> MostrarDatos()
    {
        Cliente cliente = new Cliente();
        ObservableList<Cliente> cli;

        cli = cliente.MostrarRegistros();

        return cli;
    }
    //Metodo estatico que realiza ka busqueda de dato, el parametro que resibe es el string a buscar. Y retorna una coleccion
    public static ObservableList<Cliente> BuscarDato(String a)
    {
        Cliente cliente = new Cliente();
        ObservableList<Cliente> cli;

        cli = cliente.BuscarRegistro(a);

        return cli;
    }

    public static void estadoCliente(String a) throws Exception
    {
        Cliente c = new Cliente();
        c.estadoCliente(a);
    }

    public static void modificarDatos(String a, String b , String c, String d) throws  Exception
    {
        Cliente s = new Cliente();
        s.ModificarDatos(a,b,c,d);
    }
}

