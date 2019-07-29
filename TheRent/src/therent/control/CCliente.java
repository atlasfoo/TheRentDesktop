package therent.control;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import therent.model.Cliente;

import java.util.concurrent.ExecutionException;

public class CCliente {

    public static void IngresarCliente(String a, String b, String c, String d, String e, String f, String g) throws Exception
    {
            Cliente cliente = new Cliente(a, b, c, d, e, f, g);
            cliente.IngresarCliente(cliente);

    }

    public static ObservableList<Cliente> MostrarDatos()
    {
        Cliente cliente = new Cliente();
        ObservableList<Cliente> cli;

        cli = cliente.MostrarRegistros();

        return cli;
    }
}
