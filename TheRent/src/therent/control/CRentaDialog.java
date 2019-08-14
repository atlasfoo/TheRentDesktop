package therent.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import therent.model.*;

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

    //Metodo estatico que realiza la busqueda de auto disponible
    public static ObservableList<Auto> BuscarDatoAuto(LocalDate fe, LocalDate fr) throws Exception
    {
            DetalleRentaModel auto = new DetalleRentaModel();
            ObservableList<Auto> autos ;
            autos = auto.disponibilidad_auto(Date.valueOf(fe),Date.valueOf(fr));
            msgevr("Registro encontrado.");
            return autos;
        //return MostrarDatos();

    }

    //Metodo para mandar mensajes
    public void msgerr(String msg){
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }


    //metodo para mandar mensajes de correcto
    private static void msgevr(String msg) {
        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("TheRent Link System");
        al.setHeaderText("Correcto.");
        al.setContentText(msg);
        al.showAndWait();
    }
}
