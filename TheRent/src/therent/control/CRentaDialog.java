package therent.control;

import javafx.collections.ObservableList;
import therent.model.DetalleRentaModel;
import therent.model.RentaModel;
import therent.model.TablaVistaA;
import therent.model.TablaVistaC;

import java.sql.Date;
import java.time.LocalDate;

public class CRentaDialog
{
    public static void addRenta(int Id_Cliente, String Estado) throws Exception{
        RentaModel rentaModel = new RentaModel(Id_Cliente,Estado);
        rentaModel.IngresarRenta(rentaModel);
    }

    //Metodo estatico para mostrar los datos. Y retorna una coleccion
    public static ObservableList<TablaVistaC> MostrarClientes()
    {
        TablaVistaC datos_cliente = new TablaVistaC();
        ObservableList<TablaVistaC> cli;

        cli = datos_cliente.MostrarClientes();

        return cli;
    }

    public static ObservableList<TablaVistaA> MostrarAuto()
    {
        TablaVistaA datos_Auto = new TablaVistaA();
        ObservableList<TablaVistaA> aut;

        aut = datos_Auto.MostrarAutos();

        return aut;
    }

    public static void addDetalleRenta(int idauto, LocalDate fechaentrega, LocalDate fecharecibo, Double costo) throws Exception{
        DetalleRentaModel detalleRentaModel = new DetalleRentaModel(idauto, Date.valueOf(fechaentrega),
                Date.valueOf(fecharecibo),costo);
        detalleRentaModel.IngresarDetalleRenta(detalleRentaModel);
    }
}
