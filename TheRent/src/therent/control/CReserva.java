package therent.control;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import therent.model.Reserva;
import therent.model.TablaVistaA;
import therent.model.TablaVistaC;

import java.util.Date;

public class CReserva {
    public static void Ingresar_Reserva(int idcliente, String status, int idauto, Date fecha_entrega, Date fecha_recibo, Double costo) throws Exception{
        Reserva reserva = new Reserva(idcliente,status,idauto,fecha_entrega,fecha_recibo,costo);
        reserva.IngresarReserva(reserva);
    }

    public static ObservableList<Reserva> MostrarDatos()
    {
        Reserva reserva = new Reserva();
        ObservableList<Reserva> res;

        res = reserva.MostrarRegistros();

        return res;
    }

    public static ObservableList<Reserva> BuscarRegistro(String a)
    {
        Reserva reserva = new Reserva();
        ObservableList<Reserva> res;

        res = reserva.BuscarRegistro(a);

        return res;
    }

    public static ObservableList<TablaVistaC> MostrarClientes()
    {
        TablaVistaC cliente = new TablaVistaC();
        ObservableList<TablaVistaC> cli;

        cli = cliente.MostrarClientes();

        return cli;
    }

    public static ObservableList<TablaVistaA> MostrarAutos()
    {
        TablaVistaA auto = new TablaVistaA();
        ObservableList<TablaVistaA> aut;

        aut = auto.MostrarAutos();

        return aut;
    }
}
