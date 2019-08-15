package therent.control;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import therent.model.*;

import java.sql.Date;
import java.time.LocalDate;

public class CRentaDialog
{
    //agregar la renta
    public static void addRenta(int Id_Cliente, String Estado) throws Exception{
        if(Id_Cliente < 0 || Estado.equals("") || Estado == null) {
            msgerr("No se pueden guardar los datos, verificar que ningún campo este vacío.");

        }
        else {
            RentaModel rentaModel = new RentaModel(Id_Cliente, Estado);
            rentaModel.IngresarRenta(rentaModel);
        }
    }

    //Metodo estatico para mostrar los datos. Y retorna una coleccion
    public static ObservableList<TablaVistaC> MostrarClientes()
    {
        TablaVistaC datos_cliente = new TablaVistaC();
        ObservableList<TablaVistaC> cli;

        cli = datos_cliente.MostrarClientes();

        return cli;
    }

    //muestra las rentas guardadas o ya ingresadas en el momento
    public static ObservableList<RentaModel> MostrarRenta() throws Exception {
        RentaModel datos_renta = new RentaModel();
        ObservableList<RentaModel> ren;

        ren = datos_renta.MostrarRegistrosRenta();

        return ren;
    }

    public static void addDetalleRenta(int idauto, LocalDate fechaentrega, LocalDate fecharecibo, Double costo) throws Exception{
        if(idauto < 0 ||
                fechaentrega.equals("") || fechaentrega == null ||
                fecharecibo.equals("") || fecharecibo == null ||
                costo < 0)
        {
            msgerr("No se pueden guardar los datos, verificar que ningún campo este vacío.");

        }
        else {
            DetalleRentaModel detalleRentaModel = new DetalleRentaModel(idauto, Date.valueOf(fechaentrega),
                    Date.valueOf(fecharecibo), costo);
            detalleRentaModel.IngresarDetalleRenta(detalleRentaModel);
        }
    }

    //Metodo estatico que realiza la busqueda de auto disponible
    public static ObservableList<Auto> BuscarDatoAuto(LocalDate fe, LocalDate fr) throws Exception
    {
        if(fe.equals("") || fe == null ||
                fr.equals("") || fr == null)
        {
            msgerr("No se pueden guardar los datos, verificar que ningún campo este vacío.");

        }
        else {
            DetalleRentaModel auto = new DetalleRentaModel();
            ObservableList<Auto> autos;
            autos = auto.disponibilidad_auto(Date.valueOf(fe), Date.valueOf(fr));
            msgevr("Registro encontrado.");
            return autos;
        }
        return MostrarDispAuto();
    }

    public static ObservableList<Auto> MostrarDispAuto() throws Exception{
        Auto datos_Auto = new Auto();
        ObservableList<Auto> aut;

        aut = datos_Auto.MostrarRegistrosAuto();

        return aut;
    }

    //Metodo para mandar mensajes
    public static void msgerr(String msg){
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
