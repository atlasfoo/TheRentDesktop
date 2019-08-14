package therent.view.Reservation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import therent.control.CRentaDialog;
import therent.model.Auto;
import therent.model.TablaVistaC;

public class RentSearchWindowController {

    @FXML
    private TableView<?> tabla_auto;

    @FXML
    private TableColumn<?, ?> column_id_auto;

    @FXML
    private TableColumn<?, ?> column_marca;

    @FXML
    private TableColumn<?, ?> column_modelo;

    @FXML
    private TableColumn<?, ?> column_color;

    @FXML
    private TableColumn<?, ?> column_año;

    @FXML
    private TableColumn<?, ?> column_transmision;

    @FXML
    private TableColumn<?, ?> column_carroceria;

    @FXML
    private TableColumn<?, ?> column_combustible;

    @FXML
    private TableColumn<?, ?> column_placa;

    @FXML
    private TableColumn<?, ?> column_vin;

    @FXML
    private TableColumn<?, ?> column_chasis;

    @FXML
    private TableColumn<?, ?> column_categoria;

    @FXML
    private TableColumn<?, ?> column_precio;

    @FXML
    private TableColumn<?, ?> column_deposito;

    @FXML
    private TableColumn<?, ?> column_habilitado;

    @FXML
    private TableColumn<?, ?> column_estado;

    @FXML
    private JFXDatePicker fx_fecha_entrega;

    @FXML
    private JFXDatePicker fx_fecha_recibo;

    @FXML
    private JFXButton fx_button_search;

    @FXML
    private JFXButton fx_button_modificar;

    @FXML
    private JFXButton fx_button_eliminar;

    public void MostrandoDatosCliente()
    {
        //columnas
        //column_id_auto.setCellValueFactory(new PropertyValueFactory<Auto,String>("Id_Auto"));
        //tabla_auto.setItems(CRentaDialog.MostrarAuto());
        //Cuando se da click mostrar los registros en sus respectivos campos
        //GestionEvento();
    }

    @FXML
    private void ButtonSearchCar(ActionEvent actionEvent)
    {
        try{
            CRentaDialog.BuscarDatoAuto(fx_fecha_entrega.getValue(),fx_fecha_recibo.getValue());
            //msjText2("LA RESERVA SE AGREGO CORRECTAMENTE");
        }catch (Exception E){
            msgerr(E.getMessage());
        }

    }

    //Evento para mandar mensajes
    public void msgerr(String msg){
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }
}
